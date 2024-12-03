import {useFormik} from "formik";
import React, {useState} from 'react';
import {
    Button, Grid, IconButton, InputAdornment, TextField,
    Tooltip, Typography, Paper
} from "@mui/material";
import * as Yup from 'yup';
import sha1 from "sha1";
import {toast} from "react-toastify";
import InfoIcon from '@mui/icons-material/Info';
import {useNavigate} from "react-router-dom";
import logo from "../llgo.png";

const validationSchema = Yup.object({
    email: Yup.string().email('Invalid email').required('Email is required').max(100, 'E-mail can have max 100 characters.'),
    name: Yup.string().required('Name is required').max(50, 'Name can have max 50 characters.'),
    surname: Yup.string().required('Surname is required').max(100, 'Surname can have max 100 characters.'),
    password: Yup.string().required('Password is required').matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=]).{8,}$/,
        'Not valid password'
    ).max(100, 'Password can have max 100 characters.'),
});

const url = process.env.REACT_APP_API_URL;

export const RegistrationForm = () => {
    const [checked, setChecked] = useState(false);
    const navigate = useNavigate();

    const formik = useFormik({
        initialValues: {
            email: '',
            name: '',
            surname: '',
            password: '',
        },
        validationSchema: validationSchema,
        onSubmit: async (values) => {
            const newPassword = values.password;
            const hashedPassword = sha1(newPassword).toUpperCase();
            try {
                setChecked(true);
                await postToBE(values);

            } catch (error) {
                toast.error('Error checking password');
            }
        },
    });

    const postToBE = async (values) => {
        try {
            const response = await fetch('http://localhost:8088/api/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(values)
            });
            const jsonData = await response.json();
            if (!response.ok) {
                toast.error(jsonData.message);
            } else {
                navigate('/signin');
                formik.resetForm();
                toast.success('Registration Successful! Thank you for registering. You can now log in to your account.');
            }
        } catch (error) {
            toast.error('An error occurred.');
        }
    };


    //Must contain at least 8 characters, one uppercase, one lowercase, one number, and one special character @#$%^&+=

    return (
        <Grid
            container
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{minHeight: '100vh', width: '100%'}}
        >
            <Grid item xs={12} sm={12} md={12}>
                <Paper elevation={3} style={{padding: '30px', borderRadius: '10px', marginTop: '15%'}}>
                    <form onSubmit={formik.handleSubmit}>
                        <Grid container spacing={2} direction="column" alignItems="center">
                            <Grid item xs={1} md={1} lg={1} sx={{
                                alignItems: 'center',
                                justifyContent: 'center', justifySelf: 'center', alignSelf: 'center'
                            }}>
                                <img src={logo} alt="Logo"
                                     style={{height: 40, paddingTop: '15%', paddingBottom: '15%'}}/>
                            </Grid>

                            <Grid item xs={12} md={12}>
                                <TextField
                                    id="name"
                                    name="name"
                                    fullWidth={true}
                                    label="Name"
                                    value={formik.values.name}
                                    onChange={formik.handleChange}
                                    error={formik.touched.name && Boolean(formik.errors.name)}
                                    helperText={formik.touched.name && formik.errors.name}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <Tooltip
                                                    title="Your first name">
                                                    <IconButton aria-label="Information">
                                                        <InfoIcon/>
                                                    </IconButton>
                                                </Tooltip>
                                            </InputAdornment>
                                        ),
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12} md={12}>
                                <TextField
                                    id="surname"
                                    name="surname"
                                    label="Surname"
                                    fullWidth={true}
                                    value={formik.values.surname}
                                    onChange={formik.handleChange}
                                    error={formik.touched.surname && Boolean(formik.errors.surname)}
                                    helperText={formik.touched.surname && formik.errors.surname}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <Tooltip
                                                    title="Your surename">
                                                    <IconButton aria-label="Information">
                                                        <InfoIcon/>
                                                    </IconButton>
                                                </Tooltip>
                                            </InputAdornment>
                                        ),
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12} md={12}>
                                <TextField
                                    id="email"
                                    name="email"
                                    label="Email"
                                    fullWidth={true}
                                    value={formik.values.email}
                                    onChange={formik.handleChange}
                                    error={formik.touched.email && Boolean(formik.errors.email)}
                                    helperText={formik.touched.email && formik.errors.email}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <Tooltip
                                                    title="Must contain valid e-mail address">
                                                    <IconButton aria-label="Information">
                                                        <InfoIcon/>
                                                    </IconButton>
                                                </Tooltip>
                                            </InputAdornment>
                                        ),
                                    }}
                                />
                            </Grid>
                            <Grid item xs={12} md={12}>
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
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <Tooltip
                                                    title="Must contain at least 8 characters, one uppercase, one lowercase, one number, and one special character @#$%^&+=
">
                                                    <IconButton aria-label="Information">
                                                        <InfoIcon/>
                                                    </IconButton>
                                                </Tooltip>
                                            </InputAdornment>
                                        ),
                                    }}
                                />
                            </Grid>

                            <Grid item xs={12} md={12}>
                                <Button type="submit" fullWidth={true} variant="contained">
                                    Submit registration
                                </Button>
                            </Grid>
                        </Grid>
                    </form>
                </Paper>
            </Grid>
        </Grid>
    );
};
