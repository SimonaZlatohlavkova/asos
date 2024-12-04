import React, { useEffect, useState } from 'react';
import {
    AppBar,
    Button,
    Toolbar,
    Link,
    Box,
    Badge,
} from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import { useSelector } from 'react-redux';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircleOutlined';
import logoQ from "../sss.png";
import { getCookie } from "../App";

const Navbar = () => {

    const [token, setToken] = useState(getCookie('auth')); // Initialize with current token
    const cart = useSelector((state) => state.cart);


    const cartItemCount = cart.reduce((total, item) => total + item.numberOfItems, 0);


    return (
        <>
            <AppBar position="fixed" style={{ background: '#1b4903', color: '#ffffff' }}>
                <Box sx={{ paddingLeft: '10vw', paddingRight: '10vw' }}>
                    <Toolbar>
                        {token != null &&
                            <Link component={RouterLink} to="/products/search" color="inherit" underline="none" sx={{ p: 1 }}>
                                <img src={logoQ} alt="Logo" style={{ height: 60 }} />
                            </Link>
                        }
                        {token == null &&
                            <Link component={RouterLink} to="/home" color="inherit" underline="none" sx={{ p: 1 }}>
                                <img src={logoQ} alt="Logo" style={{ height: 60 }} />
                            </Link>
                        }
                        <Box flexGrow={1} />

                        {token != null &&
                            <Link component={RouterLink} to="/profile" color="inherit" underline="none" sx={{ p: 1 }}>
                                <AccountCircleOutlinedIcon />
                            </Link>
                        }
                        {token != null &&
                            <Link component={RouterLink} to="/cart" color="inherit" underline="none" sx={{ p: 1 }}>
                                <Badge
                                    badgeContent={cartItemCount}
                                    color="error"
                                    overlap="circular"
                                    anchorOrigin={{
                                        vertical: 'top',
                                        horizontal: 'right',
                                    }}
                                >
                                    <ShoppingCartOutlinedIcon />
                                </Badge>
                            </Link>
                        }
                    </Toolbar>
                </Box>
            </AppBar>
        </>
    );
};

export default Navbar;
