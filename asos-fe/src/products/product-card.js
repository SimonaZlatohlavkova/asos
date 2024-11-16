import {
    Box,
    Button,
    Card,
    CardContent,
    CardMedia,
    Grid,
    InputAdornment,
    TextField,
    Tooltip,
    Typography
} from "@mui/material";
import StarIcon from '@mui/icons-material/Star';
import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import AddShoppingCartOutlinedIcon from '@mui/icons-material/AddShoppingCartOutlined';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import RemoveCircleIcon from '@mui/icons-material/RemoveCircle';
import IconButton from '@mui/material/IconButton';
import InfoIcon from "@mui/icons-material/Info";
import Stack from '@mui/material/Stack';
import {addToCart} from "../cart/CartRedux";
import {useDispatch, useSelector} from "react-redux";

export const ProductCard = (props) => {
    const navigate = useNavigate();
    const [number, setNumber] = useState(props.product.stock >0 ?1 :0)
    const dispatch = useDispatch();
    const cart = useSelector((state) => state.cart);
    const handleClick = (id, numberOfItems) => {
        if (numberOfItems > 0) {
            dispatch(addToCart({id: id, numberOfItems: numberOfItems}))
        }
    }

    return (
        <Grid item xs={12} md={12} lg={12}>
            <Card
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'space-between',
                    minHeight: '420px' // Ensures uniform card height
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
                        {props.product.stock > 0 ? <Typography variant="body2" color="text.secondary">
                            Available {props.product.stock} pc
                        </Typography> : <Typography variant="body2" color="#ff1313">
                            Out of stock
                        </Typography>}
                    </Box>

                    {/* Button and Price at the bottom */}
                    <Grid container justifyContent="space-between" alignItems="center">
                        <Stack directino={"column"} alignItems="center">
                            <Stack direction={"row"} spacing={1} alignItems="center">
                                <IconButton
                                    disabled={number <= 0}
                                    onClick={() => {
                                        setNumber(number - 1)
                                    }}
                                >
                                    <RemoveCircleIcon></RemoveCircleIcon>
                                </IconButton>
                                <div>{number}</div>
                                <IconButton
                                    disabled={number >= props.product.stock}
                                    onClick={() => {
                                        setNumber(number + 1)
                                    }}
                                >
                                    <AddCircleIcon></AddCircleIcon>
                                </IconButton>
                            </Stack>
                            <Button
                                variant="contained"
                                onClick={() => {
                                    handleClick(props.product.id, number)
                                }}
                            >
                                <AddShoppingCartOutlinedIcon></AddShoppingCartOutlinedIcon>
                            </Button>
                        </Stack>
                        {props.product.salePrice != null ? <Box style={{
                                backgroundColor: "#ff1313",
                                padding: "1rem",
                                borderRadius: '3px',
                                boxShadow: "5px 5px #888888"
                            }}>
                                <Typography component="div" variant="subtitle2" sx={{textDecoration: 'line-through'}}>
                                    {props.product.originalPrice.toFixed(2)} €
                                </Typography>
                                <Typography component="div" variant="h6" sx={{fontWeight: "bold", color: "white"}}>
                                    {props.product.salePrice.toFixed(2)} €
                                </Typography>


                            </Box> :
                            <Box style={{
                                backgroundColor: "#fffefe",
                                padding: "1rem",
                                borderRadius: '3px',
                                border: "1px solid gray"
                            }}>
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
