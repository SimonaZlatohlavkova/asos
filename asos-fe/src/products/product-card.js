import {Box, Button, Card, CardContent, CardMedia, Grid, Typography} from "@mui/material";
import StarIcon from '@mui/icons-material/Star';
import {useNavigate} from "react-router-dom";
import React from "react";
import AddShoppingCartOutlinedIcon from '@mui/icons-material/AddShoppingCartOutlined';

export const ProductCard = (props) => {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/product/detail/" + props.product.id);
    }

    return (
        <Grid item xs={12} md={12} lg={12}>
            <Card
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'space-between',
                    minHeight: '400px' // Ensures uniform card height
                }}
            >
                {/* Product Image */}
                <CardMedia
                    component="img"
                    height="150" // Adjust height as per your design
                    image={props.product.url}
                    sx={{objectFit: "contain", paddingTop: "2rem"}}
                    alt="product"
                />

                {/* Product Content */}
                <CardContent
                    sx={{flexGrow: 1, display: 'flex', flexDirection: 'column', justifyContent: 'space-between'}}>
                    <Box>
                        <Grid container justifyContent="space-between" alignItems="flex-start">
                            <Typography component="div" variant="h5">
                                {props.product.name}
                            </Typography>
                            {props.product.top && (
                                <Typography variant="body2" style={{fontWeight: 'bold', color: 'black'}}>
                                    <StarIcon/>
                                </Typography>
                            )}
                        </Grid>

                        <Typography variant="body2" color="text.secondary" gutterBottom>
                            {props.product.description}
                        </Typography>
                    </Box>

                    {/* Button and Price at the bottom */}
                    <Grid container justifyContent="space-between" alignItems="center">
                        <Button
                            variant="contained"
                            sx={{alignSelf: 'flex-end',}} // Button aligned to the left
                            onClick={handleClick}
                        >
                            <AddShoppingCartOutlinedIcon></AddShoppingCartOutlinedIcon>
                        </Button>
                        {props.product.salePrice != null ? <Box style={{backgroundColor: "#ff1313", padding: "1rem", borderRadius: '3px',boxShadow: "5px 5px #888888"}}>
                                <Typography component="div" variant="subtitle2" sx={{ textDecoration: 'line-through'}}>
                                    {props.product.originalPrice.toFixed(2)} €
                                </Typography>
                                <Typography component="div" variant="h6"  sx={{ fontWeight:"bold", color:"white"}}>
                                    {props.product.salePrice.toFixed(2)} €
                                </Typography>


                            </Box> :
                            <Box style={{backgroundColor: "#fffefe", padding: "1rem", borderRadius: '3px', border: "1px solid gray"}}>
                                <Typography component="div" variant="h6">
                                    {props.product.originalPrice.toFixed(2)} €
                                </Typography>
                            </Box>
                        }


                    </Grid>
                </CardContent>
            </Card>
        </Grid>
    );
};
