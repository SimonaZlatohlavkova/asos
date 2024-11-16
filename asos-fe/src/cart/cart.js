import React, { useEffect, useState } from 'react';
import {
    Grid,
    Paper,
    Typography,
    Button,
    IconButton,
    TextField,
    Stepper,
    Step,
    StepLabel,
    Box,
    Divider
} from "@mui/material";
import { Delete } from '@mui/icons-material';
import { useDispatch, useSelector } from "react-redux";
import { dummyProducts } from "../products/poduts-page";
import { addToCart, decrementItemQuantity, removeFromCart, updateCartItems } from "./CartRedux";
import Stack from "@mui/material/Stack";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';

const validationSchema = Yup.object({
    street: Yup.string().required('Street Address is required'),
    country: Yup.string().required('Country is required'),
    houseNumber: Yup.string().required('House number is required'),
    city: Yup.string().required('City is required'),
    postCode: Yup.string()
        .matches(/^[0-9]{5}$/, 'Postal Code must be a 5 digit number')
        .required('Postal Code is required'),
});

export const CartPage = () => {
    const dispatch = useDispatch();
    const cart = useSelector((state) => state.cart);
    const [loadedPage, setLoaded] = useState(false);

    useEffect(() => {
        dispatch(updateCartItems(dummyProducts));
        setLoaded(true);
    }, []);

    const [activeStep, setActiveStep] = useState(0);

    const steps = ['Cart', 'Address', 'Order'];

    const handleRemoveItem = (id) => {
        dispatch(removeFromCart({ id: id }));
    };

    const calculateTotal = () => {
        var total = 0;
        cart.forEach(item => {
            const itemTotal = item.salePrice != null
                ? +(item.salePrice * item.numberOfItems).toFixed(2)
                : +(item.originalPrice * item.numberOfItems).toFixed(2);
            total = (total + itemTotal)
        })
        return total.toFixed(2);
    };

    const handleNext = () => {
        setActiveStep((prevStep) => prevStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevStep) => prevStep - 1);
    };

    return loadedPage ? (
        <Box sx={{
            paddingLeft: '10vw',
            paddingRight: '10vw',
            marginTop: '100px',
            marginLeft: '2%',
            marginRight: '2%',
            paddingBottom: "1rem"
        }}>
            <Grid
                container
                alignItems="center"
                justifyContent="center"
                style={{ minHeight: '100%', width: '100%' }}
            >
                <Grid item xs={12} md={12}>
                    <Paper
                        elevation={3}
                        style={{
                            padding: '30px',
                            borderRadius: '10px',
                        }}
                    >
                        <Stepper activeStep={activeStep} alternativeLabel style={{ marginBottom: '20px' }}>
                            {steps.map((label, index) => (
                                <Step key={index}>
                                    <StepLabel>{label}</StepLabel>
                                </Step>
                            ))}
                        </Stepper>

                        {activeStep === 0 && (
                            <>
                                <div style={{ maxHeight: '300px', overflowY: 'auto', paddingRight: '10px' }}>
                                    {cart.length === 0 ? (
                                        <Typography variant="h6">Your cart is empty.</Typography>
                                    ) : (
                                        cart.map(item => (
                                            <Grid container spacing={2} key={item.id} alignItems="center"
                                                  style={{ marginBottom: '20px' }}>
                                                <Grid item xs={4}>
                                                    <Typography variant="h6">{item.name}</Typography>
                                                </Grid>
                                                <Grid item xs={2}>
                                                    <Typography
                                                        variant="body1">{item.salePrice != null ? item.salePrice.toFixed(2) : item.originalPrice.toFixed(2)}€ / pc</Typography>
                                                </Grid>
                                                <Grid item xs={3}>
                                                    <Stack direction={"row"} spacing={1} alignItems="center">
                                                        <IconButton
                                                            disabled={item.numberOfItems <= 1}
                                                            onClick={() => {
                                                                dispatch(decrementItemQuantity({ id: item.id }));
                                                            }}
                                                        >
                                                            <RemoveCircleIcon></RemoveCircleIcon>
                                                        </IconButton>
                                                        <div>{item.numberOfItems}</div>
                                                        <IconButton
                                                            disabled={item.numberOfItems >= item.stock}
                                                            onClick={() => {
                                                                dispatch(addToCart({
                                                                    id: item.id,
                                                                    numberOfItems: 1
                                                                }))
                                                            }}
                                                        >
                                                            <AddCircleIcon></AddCircleIcon>
                                                        </IconButton>
                                                    </Stack>
                                                </Grid>
                                                <Grid item xs={2}>
                                                    <Typography variant="body1">
                                                        {item.salePrice != null ? (item.salePrice.toFixed(2) * item.numberOfItems).toFixed(2) : (item.originalPrice.toFixed(2) * item.numberOfItems).toFixed(2)} €
                                                    </Typography>
                                                </Grid>
                                                <Grid item xs={1}>
                                                    <IconButton onClick={() => handleRemoveItem(item.id)}
                                                                style={{ color: '#881414' }}
                                                    >
                                                        <Delete />
                                                    </IconButton>
                                                </Grid>
                                            </Grid>
                                        ))
                                    )}
                                </div>

                                {cart.length > 0 && (
                                    <>
                                        <Divider style={{ margin: '20px 0' }} />

                                        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                                            <Typography variant="h5" style={{ marginTop: '20px' }}>
                                                Total: {calculateTotal()} €
                                            </Typography>
                                            <div style={{
                                                marginTop: '20px',
                                                display: 'flex',
                                                justifyContent: 'space-between'
                                            }}>
                                                <Button variant="contained" color="secondary" onClick={handleNext}>
                                                    Proceed to Address
                                                </Button>
                                            </div>
                                        </div>
                                    </>
                                )}
                            </>
                        )}

                        {activeStep === 1 && (
                            <>
                                <Typography variant="body1" gutterBottom>
                                    Please enter your shipping address.
                                </Typography>

                                {/* Address Form using Formik and Yup */}
                                <Formik
                                    initialValues={{
                                        street: '',
                                        city: '',
                                        postCode: '',
                                        country: '',
                                        houseNumber: ''
                                    }}
                                    validationSchema={validationSchema}
                                    onSubmit={(values) => {
                                        console.log('Form Values:', values);
                                    }}
                                >
                                    {({ values, handleChange, handleBlur, errors, touched }) => (
                                        <Form>
                                            <Grid container spacing={2}>
                                                <Grid item xs={12}>
                                                    <Field
                                                        as={TextField}
                                                        label="Street"
                                                        name="street"
                                                        fullWidth
                                                        size="small"
                                                        margin="dense"
                                                        value={values.street}
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        helperText={<ErrorMessage name="street" />}
                                                        error={touched.street && !!errors.street}
                                                    />
                                                </Grid>

                                                <Grid item xs={12} md={6}>
                                                    <Field
                                                        as={TextField}
                                                        label="House Number"
                                                        name="houseNumber"
                                                        fullWidth
                                                        size="small"
                                                        margin="dense"
                                                        value={values.houseNumber}
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        helperText={<ErrorMessage name="houseNumber" />}
                                                        error={touched.houseNumber && !!errors.houseNumber}
                                                    />
                                                </Grid>
                                                <Grid item xs={12} md={6}>
                                                    <Field
                                                        as={TextField}
                                                        label="City"
                                                        name="city"
                                                        fullWidth
                                                        size="small"
                                                        margin="dense"
                                                        value={values.city}
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        helperText={<ErrorMessage name="city" />}
                                                        error={touched.city && !!errors.city}
                                                    />
                                                </Grid>

                                                <Grid item xs={12} md={6}>
                                                    <Field
                                                        as={TextField}
                                                        label="Postal Code"
                                                        name="postCode"
                                                        fullWidth
                                                        size="small"
                                                        margin="dense"
                                                        value={values.postCode}
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        helperText={<ErrorMessage name="postCode" />}
                                                        error={touched.postCode && !!errors.postCode}
                                                    />
                                                </Grid>
                                                <Grid item xs={12} md={6}>
                                                    <Field
                                                        as={TextField}
                                                        label="Country"
                                                        name="country"
                                                        fullWidth
                                                        size="small"
                                                        margin="dense"
                                                        value={values.country}
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        helperText={<ErrorMessage name="country" />}
                                                        error={touched.country && !!errors.country}
                                                    />
                                                </Grid>
                                            </Grid>

                                            <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '20px' }}>
                                                <Button variant="outlined" onClick={handleBack}>
                                                    Back to Cart
                                                </Button>
                                                <Button variant="contained" color="primary" type="submit">
                                                    Confirm Address
                                                </Button>
                                            </div>
                                        </Form>
                                    )}
                                </Formik>
                            </>
                        )}
                    </Paper>
                </Grid>
            </Grid>
        </Box>
    ) : <></>
};
