import {Button, Card, CardContent, CardMedia, Grid, Typography} from "@mui/material";
import StarIcon from '@mui/icons-material/Star';
import {useNavigate} from "react-router-dom";

export const AccommodationCard = (props) => {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/accommodation/detail/"+ props.accommodation.id)

    }
    return (<>
        <Grid item xs={8} md={8} lg={8}>
            <Card sx={{display: 'flex'}}>
                <CardMedia
                    component="img"
                    sx={{width: 160}}
                    image={props.accommodation.url}
                    alt="accommodation"
                />
                <CardContent sx={{flex: '1 0 auto'}}>
                    <Grid container justifyContent="space-between" alignItems="flex-end">
                        <Typography component="div" variant="h5">
                            {props.accommodation.name}
                        </Typography>
                        {props.accommodation.top &&
                            <Typography variant="body2" style={{fontWeight: 'bold', color: 'black'}}>
                                <StarIcon> </StarIcon>
                            </Typography>
                        }
                    </Grid>

                    <Typography variant="body2" color="text.secondary">
                        {props.accommodation.address}
                    </Typography>
                    <Typography variant="body4" color="text.secondary">
                        {props.accommodation.description}
                    </Typography>
                    <Grid container justifyContent="space-between" alignItems="flex-end">
                        <Button variant="contained" style={{marginTop: '0.3rem'}} onClick={handleClick}>More</Button>
                        <Typography component="div" variant="h5">
                            {props.accommodation.price} €
                        </Typography>
                    </Grid>
                </CardContent>
            </Card>
        </Grid>

    </>)
}