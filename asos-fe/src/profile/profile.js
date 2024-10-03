import {Box, Button, Grid, InputAdornment, Paper, TextField} from "@mui/material";
import logo from "../llgo.png";
import SearchIcon from "@mui/icons-material/Search";
import React from "react";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";

import accountLogo from "../account-circle-svgrepo-com (1).svg";
import {removeCookie} from "../App";
import {useNavigate} from "react-router-dom";

export const Profile = () => {
    const navigate = useNavigate();
    const handleSignOut = () => {
        removeCookie('auth');
        navigate('/signin');
    };
    return (<>
        <Box sx={{paddingLeft: '10vw', paddingRight: '10vw', marginTop: '100px', marginLeft: '2%', marginRight: '2%'}}>
            <Grid
                container
                spacing={2}
                alignItems="center"
                justifyContent="center"
                style={{height: '90vh'}}
            >
                <Grid item xs={12} md={12} lg={7}>
                    <Paper elevation={2} style={{padding: '30px', borderRadius: '7px'}}>
                        <Box
                            sx={{
                                display: 'flex',
                                flexDirection:'column',
                                justifyContent: 'center', // Centers horizontally
                                alignItems: 'center', // Centers vertically
                                height: '100%', // Ensure the Box takes the full height of the Paper
                            }}
                        >

                            <img src={accountLogo} alt="Logo" style={{height: 70, paddingTop:'5%',paddingBottom:'5%', }}/>

                            <Button
                                variant="contained"

                                onClick={handleSignOut}
                            >
                                Sign Out
                            </Button>
                        </Box>

                    </Paper>
                </Grid>
            </Grid>
        </Box>

    </>
)
}