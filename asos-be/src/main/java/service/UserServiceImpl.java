package service;

import dto.*;
import exception.NotFoundException;
import exception.RegistrationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.OrderMapper;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import org.apache.commons.lang3.RandomStringUtils;
import javax.security.auth.login.LoginException;
import java.util.*;
import java.util.regex.Matcher;
import io.jsonwebtoken.Jwts;
import java.util.regex.Pattern;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final IOrderService orderService;

    private final OrderMapper orderMapper;

    @Override
    public UserDto registerUser(UserRegistrationRequest registrationRequest) throws RegistrationException {
        if (registrationRequest.getEmail() == null || registrationRequest.getPublicKey() == null
                || registrationRequest.getPassword() == null || registrationRequest.getEmail().isEmpty()){
            throw  new RegistrationException("Email and public key has to be provided");
        }
        if(!emailCheck(registrationRequest.getEmail())){
            throw new RegistrationException("Invalid email format");
        }
        Optional<User> existingUser = this.userRepository.findByEmail(registrationRequest.getEmail());
        if(existingUser.isPresent()) {
            throw new RegistrationException("User with this email already exist");
        }
        else{
            if(registrationRequest.getPassword().length() < 8){
                throw new RegistrationException("Password should be at least 8 characters long");
            }
            else if (!safePasswordCheck(registrationRequest.getPassword())){
                throw new RegistrationException("Password should contain at least: 1 uppercase, 1 lowercase, 1 digit and one of special characters: @#$%^&+=");
            }
            User user = new User();
            user.setName(registrationRequest.getName());
            user.setSurname(registrationRequest.getSurname());
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            this.userRepository.save(user);
            return new UserDto(user.getUserId(),user.getName(), user.getSurname(), user.getEmail(), null);
        }
    }

    @Override
    public UserDto loginUser(UserLoginRequest loginRequest) throws NotFoundException {
        Optional<User> existingUser = this.userRepository.findByEmail(loginRequest.getEmail());
        if(existingUser.isEmpty()) {
            throw new NotFoundException("Email or password is not correct");
        }
        else if(!bCryptPasswordEncoder.matches(loginRequest.getPassword(), existingUser.get().getPassword())){
            throw new NotFoundException("Email or password is not correct");
        }
        else {
            io.jsonwebtoken.JwtBuilder builder = Jwts.builder();
            builder.setSubject(existingUser.get().getUserId().toString());
            builder.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
            builder.signWith(
                    SignatureAlgorithm.HS256,
                    TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
            );
            String jwt = builder
                    .compact();
            return new UserDto(existingUser.get().getUserId(), existingUser.get().getName(), existingUser.get().getSurname(), existingUser.get().getEmail(), jwt);
        }
        }

    @Override
    public ValidationDto isValidToken(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                    .parseClaimsJws(jwt)
                    .getBody();
            return new ValidationDto(true);
        }
        catch (Exception e){
            return new ValidationDto(false);
        }
    }

    @Override
    public List<OrderResponse> getUserOrders(Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    public User getById(Long id) throws NotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User with this id:" + id + " doesn't exist");
        }
        return user.get();
    }

    @Override
    public Long getUserIdByToken(String jwt) throws LoginException{
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                    .parseClaimsJws(jwt)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        }
        catch (Exception e){
            throw new LoginException("Invalid token");
        }

    }

    @Override
    public UserProfileResponse getUserProfile(Long userId) throws NotFoundException{
        var user = getById(userId);
        return new UserProfileResponse(user.getName(), user.getSurname(), user.getEmail());
    }

    public static boolean safePasswordCheck(String password) {
        String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,255}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean emailCheck(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(16);
    }
}
