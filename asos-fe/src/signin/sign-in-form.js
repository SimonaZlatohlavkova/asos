import {useFormik} from "formik";
import React, {useState} from 'react';
import {Button, Grid, Paper, TextField, Typography} from "@mui/material";
import * as Yup from 'yup';
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";
import {setCookie} from "../App";
import logo from "../llgo.png";

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
            try {
                const response = await fetchWithRateLimit(url, values);
            } catch (error) {
                if (error instanceof Error) {
                    toast.error(error.message); // Display the error message in a toast
                } else {
                    const errorMessage = await error.text();
                    const errorObject = JSON.parse(errorMessage);
                    toast.error(errorObject.error); // Display the custom backend exception message in a toast
                }
            }
        },
    });

    const fetchWithRateLimit = async (url, options) => {
        return new Promise((resolve, reject) => {
            const makeRequest = async () => {
                const currentTime = new Date().getTime();
                if (currentTime - lastRequestTime < requestInterval) {
                    setRequestCount(requestCount + 1);
                    setLastRequestTime(currentTime)
                    if (requestCount > maxRequests) {
                        reject(new Error('Too many requests, please try again later.'));
                        return;
                    }
                } else {
                    setRequestCount(1)
                    setLastRequestTime(currentTime)
                }

                try {
                    const response = await fetch(url + '/api/login', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(options)
                    });
                    const jsonData = await response.json();

                    if (!response.ok) {
                        toast.error(jsonData.message)
                    } else {
                        setCookie('auth', jsonData.jwt, 30);
                        navigate("/home");
                    }
                } catch (error) {
                    reject(error);
                }
            };

            makeRequest();
        });
    };

    return (
        <Grid
            container
            spacing={2}
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ minHeight: '100vh', width: '100%' }}
        >
            <Grid item xs={12} sm={12} md={6}>
                <Paper elevation={2} style={{ padding: '30px', borderRadius: '7px' }}>
                    <form onSubmit={formik.handleSubmit}>
                        <Grid container spacing={2} direction="column" alignItems="center">
                            <Grid item xs={1} md={1} lg={1}  sx={{alignItems:'center',
                                justifyContent: 'center', justifySelf:'center', alignSelf:'center'}}>
                                <img src={logo} alt="Logo" style={{height: 70, paddingTop:'15%',paddingBottom:'15%'}}/>
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
};
