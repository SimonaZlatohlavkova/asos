import React from 'react';
import {
    Box,
    Card,
    CardContent,
    Typography,
    Accordion,
    AccordionSummary,
    AccordionDetails,
    List,
    ListItem,
    ListItemText,
    ListItemAvatar,
    Avatar,
    Divider,
    Grid
} from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

 export const Orders = ({ orders }) => {
    return (
        <Box sx={{ padding: '20px', display: 'flex', flexDirection: 'column', gap: 2, paddingLeft: '10vw',
            paddingRight: '10vw',
            marginTop: '100px',
            marginLeft: '2%',
            marginRight: '2%',
            paddingBottom: "1rem" }}>
            {orders.map((order) => (
                <Card key={order.id} sx={{ backgroundColor: '#f9f9f9', borderRadius: '10px' }}>
                    <CardContent>
                        <Typography variant="h6">Order ID: {order.id}</Typography>
                        <Typography variant="body1">
                            <strong>Date:</strong> {order.date}
                        </Typography>
                        <Typography variant="body1">
                            <strong>Address:</strong> {`${order.address.street} ${order.address.houseNumber}, ${order.address.city}, ${order.address.postCode}, ${order.address.country}`}
                        </Typography>
                        <Typography variant="body1">
                            <strong>Status:</strong> {order.status}
                        </Typography>
                        <Typography variant="body1">
                            <strong>Total:</strong> {(order.summarization).toFixed(2)} €
                        </Typography>


                        {/* Products Accordion */}
                        <Accordion sx={{ marginTop: 2 }}>
                            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                                <Typography variant="body1">Products ({order.products.length})</Typography>
                            </AccordionSummary>
                            <AccordionDetails>
                                <List>
                                    {order.products.map((product) => (
                                        <React.Fragment key={product.id}>
                                            <ListItem alignItems="flex-start">
                                                <ListItemAvatar>
                                                    <Avatar
                                                        alt={product.name}
                                                        src={product.url}
                                                        sx={{ width: 56, height: 56 }}
                                                    />
                                                </ListItemAvatar>
                                                <ListItemText
                                                    primary={`${product.name}`}
                                                    secondary={`Price: ${product.price.toFixed(2)} € | Quantity: ${product.quantity}`}
                                                />
                                            </ListItem>
                                            <Divider variant="inset" component="li" />
                                        </React.Fragment>
                                    ))}
                                </List>
                            </AccordionDetails>
                        </Accordion>
                    </CardContent>
                </Card>
            ))}
        </Box>
    );
};

