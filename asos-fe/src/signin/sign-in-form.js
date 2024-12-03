import {useFormik} from "formik";
import React, {useState} from 'react';
import {Button, Grid, Paper, TextField, Typography} from "@mui/material";
import * as Yup from 'yup';
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";
import {setCookie} from "../App";
import logo from "../llgo.png";
import {fetchWithRateLimit} from "../fetch-with-rate-limits";
import {fetchWithRateLimitsNoToken} from "../fetch-with-rate-limits-no-token";

const validationSchema = Yup.object({
    email: Yup.string().email('Invalid email').required('Email is required').max(100, 'E-mail can have max 100 characters.'),
    password: Yup.string().required('Password is required').max(100, 'Password can have max 100 characters.'),
});
const url = process.env.REACT_APP_API_URL
export const SignInForm = () => {
        const [maxRequests, setMaxRequest] = useState(3);
        const [requestInterval, setRequestInterval] = useState(5000);
        const [requestCount, setRequestCount] = useState(0);
        const [lastRequestTime, setLastRequestTime] = useState(0);
        const navigate = useNavigate();
        const formik = useFormik({
            initialValues: {
                email: '',
                password: '',
            },
            validationSchema: validationSchema,
            onSubmit: async (values) => {
                const response = await fetchWithRateLimitsNoToken(values, lastRequestTime, setLastRequestTime, setRequestCount, requestCount, "login", navigate)
                console.log("response from BE")
                console.log(response)
                const jsonData = response.json();
                console.log(jsonData)
                if (jsonData) {
                    setCookie('auth', jsonData.jwt, 30)
                    navigate("/products/search")
                }
            }
        });

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
                        <form onSubmit={formik.handleSubmit}>
                            <Grid container spacing={2} direction="column" alignItems="center">
                                <Grid item xs={1} md={1} lg={1} sx={{
                                    alignItems: 'center',
                                    justifyContent: 'center', justifySelf: 'center', alignSelf: 'center'
                                }}>
                                    <img src={logo} alt="Logo"
                                         style={{height: 70, paddingTop: '15%', paddingBottom: '15%'}}/>
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        id="email"
                                        name="email"
                                        label="Email"
                                        fullWidth={true}
                                        value={formik.values.email}
                                        onChange={formik.handleChange}
                                        error={formik.touched.email && Boolean(formik.errors.email)}
                                        helperText={formik.touched.email && formik.errors.email}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        id="password"
                                        name="password"
                                        label="Password"
                                        type="password"
                                        fullWidth={true}
                                        value={formik.values.password}
                                        onChange={formik.handleChange}
                                        error={formik.touched.password && Boolean(formik.errors.password)}
                                        helperText={formik.touched.password && formik.errors.password}
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <Button type="submit" fullWidth={true} variant="contained" color="secondary">
                                        Sign In
                                    </Button>
                                </Grid>
                            </Grid>
                        </form>
                    </Paper>
                </Grid>
            </Grid>
        );
    }
;
