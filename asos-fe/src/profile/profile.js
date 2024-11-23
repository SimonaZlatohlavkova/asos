import {Box, Button, Grid, InputAdornment, Paper, TextField, Typography} from "@mui/material";
import logo from "../llgo.png";
import SearchIcon from "@mui/icons-material/Search";
import React, {useEffect, useState} from "react";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";

import accountLogo from "../account-circle-svgrepo-com (1).svg";
import {removeCookie} from "../App";
import {useNavigate} from "react-router-dom";
import {fetchWithRateLimitsGET} from "../fetch-with-rate-limits-get";
const dummyProfile={
    "name": "Simona",
    "surname": "Zlatohlávková",
    "email": "email@gmail.com"
}

export const Profile = () => {
    const navigate = useNavigate();
    const [profile, setProfile]=useState(dummyProfile)
    const [requestCount, setRequestCount] = useState(0)
    const [lastRequestTime, setLastRequestTime] = useState(0)
    const handleSignOut = () => {
        removeCookie('auth');
        navigate('/signin');
    };

    useEffect(() => {
        fetchData()
    }, []);

    const fetchData=async () => {
        const response = await fetchWithRateLimitsGET(lastRequestTime, setLastRequestTime, setRequestCount, requestCount, "user/profile", navigate)
        console.log("response from BE")
        console.log(response)
        const jsonData = await response.json()
        if (jsonData) {
            setProfile(jsonData)
        }
    }

    return (<>
        <Box sx={{paddingLeft: '10vw', paddingRight: '10vw', marginTop: '100px', marginLeft: '2%', marginRight: '2%'}}>
            <Grid
                container
                spacing={2}
                alignItems="center"
                justifyContent="center"
                style={{height: '90vh'}}
            >
                <Grid item xs={12} md={12} lg={5}>
                    <Paper elevation={2} style={{ padding: '30px', borderRadius: '7px' }}>
                        <Box
                            sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'center', // Centers horizontally
                                alignItems: 'center', // Centers vertically
                                height: '100%', // Ensure the Box takes the full height of the Paper
                            }}
                        >
                            {/* Badge for the first letter of the user's name */}
                            <Box
                                sx={{
                                    width: 70,
                                    height: 70,
                                    display: 'flex',
                                    justifyContent: 'center',
                                    alignItems: 'center',
                                    borderRadius: '50%',
                                    backgroundColor: '#797878',
                                    color: 'white',
                                    fontSize: '24px',
                                    fontWeight: 'bold',
                                    marginBottom: '20px',

                                }}
                            >
                                {profile.name ? profile.name.charAt(0).toUpperCase() : 'N'}
                            </Box>

                            {/* Name and profile details */}
                            <Typography variant="h6" gutterBottom>
                                {profile.name} {profile.surname}
                            </Typography>
                            <Typography variant="body2" color="textSecondary" gutterBottom>
                                {profile.email}
                            </Typography>

                            <Button
                                variant="contained"
                                fullWidth={true}
                                color={"secondary"}
                                style={{ marginTop: '20px', marginBottom: '10px' }}
                                onClick={() => {
                                    navigate('/orders');
                                }}
                            >
                                Show Orders History
                            </Button>
                            <Button variant="contained"   fullWidth={true} onClick={handleSignOut}>
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