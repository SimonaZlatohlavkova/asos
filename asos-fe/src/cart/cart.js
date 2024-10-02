import React, { useState } from 'react';
import { Grid, Paper, Typography, Button, IconButton, TextField } from "@mui/material";
import { Delete } from '@mui/icons-material';

const productsInCart = [
    { id: 1, name: 'Product 1', price: 50, quantity: 1 },
    { id: 2, name: 'Product 2', price: 30, quantity: 2 },
    { id: 3, name: 'Product 3', price: 100, quantity: 1 },
];

export const CartPage = () => {
    const [cartItems, setCartItems] = useState(productsInCart);

    const handleQuantityChange = (id, quantity) => {
        setCartItems(prevItems =>
            prevItems.map(item =>
                item.id === id ? { ...item, quantity: Math.max(1, quantity) } : item
            )
        );
    };

    const handleRemoveItem = (id) => {
        setCartItems(prevItems => prevItems.filter(item => item.id !== id));
    };

    const calculateTotal = () => {
        return cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    };

    return (
        <Grid
            container
            direction="column"
            alignItems="center"
            justifyContent="center"
            style={{ minHeight: '100vh', width: '100%' }}
        >
            <Grid item xs={12} md={8}>
                <Paper elevation={3} style={{ padding: '30px', borderRadius: '10px', width: '100%' }}>
                    <Typography variant="h4" gutterBottom>
                        Your Cart
                    </Typography>
                    {cartItems.length === 0 ? (
                        <Typography variant="h6">Your cart is empty.</Typography>
                    ) : (
                        cartItems.map(item => (
                            <Grid container spacing={2} key={item.id} alignItems="center" style={{ marginBottom: '20px' }}>
                                <Grid item xs={4}>
                                    <Typography variant="h6">{item.name}</Typography>
                                </Grid>
                                <Grid item xs={2}>
                                    <Typography variant="body1">${item.price}</Typography>
                                </Grid>
                                <Grid item xs={2}>
                                    <TextField
                                        type="number"
                                        value={item.quantity}
                                        onChange={(e) => handleQuantityChange(item.id, parseInt(e.target.value))}
                                        InputProps={{ inputProps: { min: 1 } }}
                                        size="small"
                                    />
                                </Grid>
                                <Grid item xs={2}>
                                    <Typography variant="body1">
                                        ${item.price * item.quantity}
                                    </Typography>
                                </Grid>
                                <Grid item xs={2}>
                                    <IconButton onClick={() => handleRemoveItem(item.id)} color="secondary">
                                        <Delete />
                                    </IconButton>
                                </Grid>
                            </Grid>
                        ))
                    )}

                    {cartItems.length > 0 && (
                        <>
                            <Typography variant="h5" style={{ marginTop: '20px' }}>
                                Total: ${calculateTotal()}
                            </Typography>
                            <Button variant="contained" color="primary" style={{ marginTop: '20px' }}>
                                Proceed to Checkout
                            </Button>
                        </>
                    )}
                </Paper>
            </Grid>
        </Grid>
    );
};
