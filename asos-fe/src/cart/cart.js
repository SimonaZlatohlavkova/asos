import React, {useEffect, useState} from 'react';
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
import {Delete} from '@mui/icons-material';
import {useDispatch, useSelector} from "react-redux";
import {dummyProducts} from "../products/poduts-page";
import {addToCart, decrementItemQuantity, removeFromCart, updateCartItems} from "./CartRedux";
import Stack from "@mui/material/Stack";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import {Formik, Field, Form, ErrorMessage} from 'formik';
import ChevronLeftRoundedIcon from '@mui/icons-material/ChevronLeftRounded';
import ChevronRightRoundedIcon from '@mui/icons-material/ChevronRightRounded';
import * as Yup from 'yup';
import {type} from "@testing-library/user-event/dist/type";
import {string} from "yup";

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
    const [adress, setAdress] = useState({})

    useEffect(() => {
        dispatch(updateCartItems(dummyProducts));
        setLoaded(true);
    }, []);

    const [activeStep, setActiveStep] = useState(0);

    const steps = ['Cart', 'Address', 'Order'];

    const handleRemoveItem = (id) => {
        dispatch(removeFromCart({id: id}));
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
                    style={{minHeight: '100%', width: '100%'}}
                >
                    <Grid item xs={12} md={12}>
                        <Paper
                            elevation={3}
                            style={{
                                padding: '30px',
                                borderRadius: '10px',
                            }}
                        >
                            <Stepper activeStep={activeStep} alternativeLabel style={{marginBottom: '20px'}}>
                                {steps.map((label, index) => (
                                    <Step key={index}>
                                        <StepLabel>{label}</StepLabel>
                                    </Step>
                                ))}
                            </Stepper>

                            {activeStep === 0 && (
                                <>
                                    <div style={{maxHeight: '50vh', overflowY: 'auto', paddingRight: '10px'}}>
                                        {cart.length === 0 ? (
                                            <Box
                                                display="flex"
                                                justifyContent="center"
                                                alignItems="center"
                                                sx={{
                                                    height: '100%', // Adjust height to fully center content in the card
                                                    textAlign: 'center',
                                                }}
                                            >
                                                <Typography
                                                    variant="h4" // Larger font size
                                                    color="gray" // Secondary color
                                                    sx={{
                                                        padding: '20px', // Adds padding for a cleaner look
                                                    }}
                                                >
                                                    Your cart is empty
                                                </Typography>
                                            </Box>
                                        ) : (
                                            cart.map(item => (
                                                <Grid container spacing={2} key={item.id} alignItems="center"
                                                      style={{marginBottom: '1%'}}>
                                                    <Grid item lg={1} md={4} xs={12}>
                                                        <img
                                                            src={item.url}
                                                            alt={item.name}
                                                            style={{width: '60%', height: 'auto', borderRadius: '5px'}}
                                                        />
                                                    </Grid>
                                                    <Grid item lg={4} md={5} xs={12}>
                                                        <Typography variant="h6">{item.name}</Typography>
                                                    </Grid>
                                                    <Grid item lg={2} md={3} xs={12}>
                                                        <Typography
                                                            variant="body1">{item.salePrice != null ? item.salePrice.toFixed(2) : item.originalPrice.toFixed(2)}€
                                                            / pc</Typography>
                                                    </Grid>
                                                    <Grid item lg={2} md={5} xs={6}>
                                                        <Stack direction={"row"} spacing={1} alignItems="center">
                                                            <IconButton
                                                                disabled={item.numberOfItems <= 1}
                                                                onClick={() => {
                                                                    dispatch(decrementItemQuantity({id: item.id}));
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
                                                    <Grid item lg={2} md={5} xs={4}>
                                                        <Typography variant="body1">
                                                            {item.salePrice != null ? (item.salePrice.toFixed(2) * item.numberOfItems).toFixed(2) : (item.originalPrice.toFixed(2) * item.numberOfItems).toFixed(2)} €
                                                        </Typography>
                                                    </Grid>
                                                    <Grid item lg={1} md={1} xs={1}>
                                                        <IconButton onClick={() => handleRemoveItem(item.id)}
                                                                    style={{color: '#881414'}}
                                                        >
                                                            <Delete/>
                                                        </IconButton>
                                                    </Grid>
                                                    <Grid item lg={12} md={12} xs={12}>
                                                        <Divider style={{margin: '2px 0'}}/>

                                                    </Grid>

                                                </Grid>
                                            ))
                                        )}
                                    </div>

                                    {cart.length > 0 && (
                                        <>

                                            <Grid container spacing={2} sx={{marginTop: "2%"}}>
                                                <Grid item lg={9} md={7} xs={12} sx={{
                                                    display: 'flex',
                                                    justifyContent: {
                                                        xs: 'center',
                                                        md: "flex-start",
                                                        lg: "flex-start"
                                                    },
                                                }}>
                                                    <Typography variant="h5">
                                                        Total: {calculateTotal()} €
                                                    </Typography>
                                                </Grid>
                                                <Grid item lg={3} md={5} xs={12} sx={{
                                                    display: 'flex',
                                                    justifyContent: {xs: 'center', sm: 'flex-end'}, // Center on small screens, space-between otherwise
                                                }}>
                                                    <Button variant="contained" fullWidth={true} color="secondary"
                                                            onClick={handleNext}>
                                                        To Address
                                                        <ChevronRightRoundedIcon></ChevronRightRoundedIcon>
                                                    </Button>

                                                </Grid>
                                            </Grid>


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
                                            setAdress(values)
                                            console.log(values)
                                            console.log('Form Values:', values);
                                            setActiveStep(2)
                                        }}
                                    >
                                        {({values, handleChange, handleBlur, errors, touched}) => (
                                            <Form>
                                                <Grid container spacing={2}>
                                                    <Grid item xs={12} md={6}>
                                                        <Field
                                                            as={TextField}
                                                            label="Street"
                                                            name="street"
                                                            fullWidth


                                                            value={values.street}
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            helperText={<ErrorMessage name="street"/>}
                                                            error={touched.street && !!errors.street}
                                                        />
                                                    </Grid>

                                                    <Grid item xs={12} md={6}>
                                                        <Field
                                                            as={TextField}
                                                            label="House Number"
                                                            name="houseNumber"
                                                            fullWidth


                                                            value={values.houseNumber}
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            helperText={<ErrorMessage name="houseNumber"/>}
                                                            error={touched.houseNumber && !!errors.houseNumber}
                                                        />
                                                    </Grid>
                                                    <Grid item xs={12} md={6}>
                                                        <Field
                                                            as={TextField}
                                                            label="City"
                                                            name="city"
                                                            fullWidth


                                                            value={values.city}
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            helperText={<ErrorMessage name="city"/>}
                                                            error={touched.city && !!errors.city}
                                                        />
                                                    </Grid>

                                                    <Grid item xs={12} md={6}>
                                                        <Field
                                                            as={TextField}
                                                            label="Postal Code"
                                                            name="postCode"
                                                            fullWidth

                                                            value={values.postCode}
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            helperText={<ErrorMessage name="postCode"/>}
                                                            error={touched.postCode && !!errors.postCode}
                                                        />
                                                    </Grid>
                                                    <Grid item xs={12} md={6}>
                                                        <Field
                                                            as={TextField}
                                                            label="Country"
                                                            name="country"
                                                            fullWidth

                                                            value={values.country}
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            helperText={<ErrorMessage name="country"/>}
                                                            error={touched.country && !!errors.country}
                                                        />
                                                    </Grid>
                                                </Grid>

                                                <Grid container spacing={2} sx={{marginTop: "2%"}}>
                                                    <Grid item lg={3} md={5} xs={12} sx={{
                                                        display: 'flex',
                                                        justifyContent: {
                                                            xs: 'center',
                                                            md: "flex-start",
                                                            lg: "flex-start"
                                                        },
                                                    }}>
                                                        <Button variant="contained" color="grey" fullWidth={true}
                                                                onClick={handleBack}>
                                                            <ChevronLeftRoundedIcon></ChevronLeftRoundedIcon>
                                                            Back to Cart
                                                        </Button>
                                                    </Grid>
                                                    <Grid item lg={6} md={2} xs={0}> </Grid>
                                                    <Grid item lg={3} md={5} xs={12} sx={{
                                                        display: 'flex',
                                                        justifyContent: {
                                                            xs: 'center',
                                                            md: "flex-end",
                                                            lg: "flex-end"
                                                        },
                                                    }}>
                                                        <Button variant="contained" fullWidth={true} color="secondary"
                                                                type="submit">
                                                            Confirm Address
                                                            <ChevronRightRoundedIcon></ChevronRightRoundedIcon>
                                                        </Button>
                                                    </Grid>
                                                </Grid>
                                            </Form>
                                        )}
                                    </Formik>
                                </>
                            )}

                            {activeStep === 2 && (

                                <>
                                    <Typography variant="h5" gutterBottom>
                                        Order Summary
                                    </Typography>

                                    {/* Order Items Summary */}
                                    <div style={{ overflowY: 'auto', marginBottom: '20px'}}>
                                        {cart.map(item => (
                                            <Grid container spacing={2} key={item.id} alignItems="center">
                                                <Grid item  lg={6} md={6} xs={8}>
                                                    <Typography variant="body1">{item.name}</Typography>
                                                </Grid>
                                                <Grid item lg={3} md={3} xs={3}>
                                                    <Typography variant="body1">x{item.numberOfItems}</Typography>
                                                </Grid>
                                                <Grid item lg={3} md={3} xs={10}>
                                                    <Typography variant="body1">
                                                        {((item.salePrice || item.originalPrice).toFixed(2) * item.numberOfItems).toFixed(2)} €
                                                    </Typography>
                                                </Grid>
                                                <Grid item xs={12} >
                                                    <Divider style={{marginBottom: '1rem'}} ></Divider>
                                                </Grid>
                                            </Grid>
                                        ))}
                                    </div>

                                    {/* Total */}
                                    <Typography variant="h6" gutterBottom>
                                        Total: {calculateTotal()} €
                                    </Typography>

                                    {/* Address Summary */}
                                    <Typography variant="h6" gutterBottom style={{marginTop: '20px'}}>
                                        Shipping Address
                                    </Typography>
                                    <Typography variant="body1">
                                        {`${adress.street} ${adress.houseNumber}`}
                                    </Typography>
                                    <Typography variant="body1">
                                        {`${adress.postCode}, ${adress.city} `}
                                    </Typography>
                                    <Typography variant="body1">
                                        {`${adress.country}`}
                                    </Typography>

                                    {/* Navigation Buttons */}
                                    <Grid container spacing={2} sx={{marginTop: "2%"}}>
                                        <Grid item lg={3} md={5} xs={12} sx={{
                                            display: 'flex',
                                            justifyContent: {
                                                xs: 'center',
                                                md: "flex-start",
                                                lg: "flex-start"
                                            },
                                        }}>
                                            <Button variant="contained" color="grey" fullWidth={true} onClick={handleBack}>
                                                <ChevronLeftRoundedIcon></ChevronLeftRoundedIcon>
                                                Back to Address
                                            </Button>
                                        </Grid>
                                        <Grid item lg={6} md={2} xs={0}> </Grid>
                                        <Grid item lg={3} md={5} xs={12} sx={{
                                            display: 'flex',
                                            justifyContent: {
                                                xs: 'center',
                                                md: "flex-end",
                                                lg: "flex-end"
                                            },
                                        }}>
                                            <Button
                                                variant="contained"
                                                fullWidth={true}
                                                color="secondary"
                                                onClick={() => {
                                                    console.log('Order Confirmed:', {
                                                        cart,
                                                        total: calculateTotal(),
                                                        address: adress
                                                    });
                                                    setActiveStep((prevStep) => prevStep + 1);
                                                }}
                                            >
                                                Confirm Order
                                                <ChevronRightRoundedIcon></ChevronRightRoundedIcon>
                                            </Button>
                                        </Grid>
                                    </Grid>

                                </>
                            )}
                            {activeStep === 3 && (
                                <Box
                                    display="flex"
                                    justifyContent="center"
                                    alignItems="center"
                                    sx={{
                                        height: '100%', // Adjust height to fully center content in the card
                                        textAlign: 'center',
                                    }}
                                >
                                    <Typography
                                        variant="h4" // Larger font size
                                        color="secondary" // Secondary color
                                        sx={{
                                            fontWeight: 'bold', // Emphasizes the text
                                            padding: '20px', // Adds padding for a cleaner look
                                        }}
                                    >
                                        Thank you for placing your order.
                                    </Typography>
                                </Box>
                            )}

                        </Paper>
                    </Grid>
                </Grid>
            </Box>
        ) :
        <></>
};
