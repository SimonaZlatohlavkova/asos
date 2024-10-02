import React, {useState} from 'react';
import {
    AppBar,
    Button,
    IconButton,
    Toolbar,
    Typography,
    Drawer,
    List,
    ListItem,
    ListItemText,
    Link,
    Box, Container
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import {Link as RouterLink, useNavigate} from 'react-router-dom';
import * as PropTypes from "prop-types";
import {getCookie, removeCookie} from "../App";
import logo from '../llgo.png';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';

Button.propTypes = {children: PropTypes.node};

const Navbar = () => {
    const navigate = useNavigate();
    const [drawerOpen, setDrawerOpen] = useState(false);

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }
        setDrawerOpen(open);
    };

    const handleSignOut = () => {
        removeCookie('auth');
        navigate('/signin');
    };


    const drawerContent = (
        <Box
            sx={{width: 250, mt: 8}} // Drawer starts below the AppBar
            role="presentation"
            onClick={toggleDrawer(false)}
            onKeyDown={toggleDrawer(false)}
        >
            <List>
                <ListItem button component={RouterLink} to="/link1">
                    <ListItemText primary="Link 1"/>
                </ListItem>
                <ListItem button component={RouterLink} to="/link2">
                    <ListItemText primary="Link 2"/>
                </ListItem>
                <ListItem button component={RouterLink} to="/link3">
                    <ListItemText primary="Link 3"/>
                </ListItem>
            </List>
        </Box>
    );

    return (
    <>
        <AppBar position="fixed" style={{background: '#1b4903', color: '#ffffff'}}>
            <Box sx={{paddingLeft:'10vw', paddingRight:'10vw'}}>
                <Toolbar>
                    {/* Hamburger Menu Icon on the left */}
                    {/*  <IconButton edge="start" color="inherit" aria-label="menu" onClick={toggleDrawer(true)}>
                        <MenuIcon/>
                    </IconButton>
                    <Typography></Typography> */}

                    {!getCookie('auth') && (
                        <>
                            <Link component={RouterLink} to="/registration" color="inherit" underline="none"
                                  sx={{p: 1}}>
                                Registration
                            </Link>
                            <Link component={RouterLink} to="/signin" color="inherit" underline="none" sx={{p: 1}}>
                                Sign In
                            </Link>

                        </>
                    )}
                    {getCookie('auth') && (<>
                            <Button
                                variant="outlined"
                                style={{color: 'white'}}
                                onClick={handleSignOut}
                            >
                                Sign Out
                            </Button>
                            <Link component={RouterLink} to="/cart" color="inherit" underline="none"
                                  sx={{p: 1}}>
                                <ShoppingCartOutlinedIcon></ShoppingCartOutlinedIcon>
                            </Link>
                        </>

                    )}
                </Toolbar>
            </Box>
        </AppBar>

        <Drawer
            anchor="left"
            open={drawerOpen}
            onClose={toggleDrawer(false)}
        >
            {drawerContent}
        </Drawer>
    </>
)
    ;
};

export default Navbar;
