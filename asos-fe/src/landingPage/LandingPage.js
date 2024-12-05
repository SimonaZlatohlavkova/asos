import {Box, Button, Grid, InputAdornment, Paper, TextField, Typography} from "@mui/material";
import logo from "../llgo.png";
import SearchIcon from "@mui/icons-material/Search";
import React, {useEffect, useState} from "react";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";

import accountLogo from "../account-circle-svgrepo-com (1).svg";
import {removeCookie} from "../App";
import {useNavigate} from "react-router-dom";
import {fetchWithRateLimitsGET} from "../fetch-with-rate-limits-get";
import ChevronRightRoundedIcon from "@mui/icons-material/ChevronRightRounded";


export const LandingPage = () => {
    const navigate = useNavigate();


    return (

        <Grid
            container
            spacing={2}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{minHeight: '100vh', width: '100%'}}
        >
            <Grid item xs={12} sm={12} md={6}>
                <Paper elevation={2} style={{padding: '30px', borderRadius: '7px', marginTop: '15%'}}>
                    <Grid container spacing={2} direction="column" alignItems="center">
                        <Grid item xs={1} md={1} lg={1} sx={{
                            alignItems: 'center',
                            justifyContent: 'center', justifySelf: 'center', alignSelf: 'center'
                        }}>
                            <img src={logo} alt="Logo"
                                 style={{height: 70, paddingTop: '15%', paddingBottom: '15%'}}/>
                        </Grid>

                        <Grid item xs={12} md={12} lg={12}>
                            <Button variant="contained" color="secondary"
                                    sx={{
                                        alignItems: 'center',
                                        width:'140px',}
                                        }
                                    onClick={() => {
                                        navigate("/signin")
                                    }}>
                                Sign  In
                            </Button>

                        </Grid>

                        <Grid item xs={12} md={12} lg={12}>
                            <Button variant="contained" color="grey" fullWidth={true}
                                    onClick={() => {
                                        navigate("/registration")
                                    }}>
                                Registration
                            </Button>
                        </Grid>
                    </Grid>

                </Paper>
            </Grid>
        </Grid>
    )
}