import {Box, Button, Grid, InputAdornment, TextField} from "@mui/material";
import React, {useState} from "react";
import {useFormik} from "formik";
import SearchIcon from '@mui/icons-material/Search';
import * as Yup from "yup";
import logo from "../llgo.png";
import {ProductCard} from "./product-card";

const validationSchema = Yup.object({
    name: Yup.string()
        .max(30, 'Name must be at most 30 characters')
        .matches(/^[a-zA-Z0-9áäčďéíĺľňóôŕšťúýžÁÄČĎÉÍĹĽŇÓÔŔŠŤÚÝŽ]+$/, 'Search can only contain letters and numbers'),
});


const dummyProducts = [{
    id: 1,
    name: "Yogurt",
    url: "https://res.cloudinary.com/riqra/image/upload/w_656,h_656,c_limit,q_auto,f_auto/v1695769979/sellers/9/rlzngadefxo9zlc0zigq.webp",
    description: "88% milk, 2% strawberry, 10% chocolate",
    originalPrice: 1.20,
    stock:8,
    salePrice: 0.89
}, {
    id: 2,
    name: "Yogurt Sophie",
    url: "https://www.dairyfoods.com/ext/resources/eNews/2012-01-np1-422.gif?1327517141",
    description: "88% milk, 12% chocolate",
    originalPrice: 0.90,
    stock:8,
    salePrice: 0.50
}, {
    id: 3,
    name: "Yogurt Danette",
    url: "https://digitalcontent.api.tesco.com/v2/media/ghs/8ee08181-50e6-40b2-8f0c-f0e3d5aa2c4a/443e9ae8-18de-4786-a918-f29198f621f0_121963493.jpeg?h=540&w=540",
    description: "50% milk, 10% dried banana powder, 12% chocolate",
    originalPrice: 1.19,
    stock:8,
    salePrice: null
}, {
    id: 4,
    name: "Yogurt Jogobela",
    url: "https://lunys.sk/wp-content/uploads/2023/03/640015ea09c28.jpg",
    description: "70% milk,  25% cherry, 5% sugar ",
    originalPrice: 0.76,
    stock:4,
    salePrice: null
}, {
    id: 5,
    name: "Yogurt Pribinacik",
    url: "https://images-polarfood-cdn.rshop.sk/lg/products/796d9ec06da7d5b306b997531753f733.jpg",
    description: "80% milk,  15% vanilla extract, 5% sugar ",
    originalPrice: 0.89,
    stock:0,
    salePrice: 0.50
}, {
    id: 6,
    name: "Yogurt Greek",
    url: "https://cdn.mafrservices.com/sys-master-root/ha9/h4a/16000929464350/1590713_main.jpg?im=Resize=1700",
    description: "100% milk",
    originalPrice: 0.80,
    stock:8,
    salePrice: null
}, {
    id: 7,
    name: "Yogurt Parmalat",
    url: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTioE4Tg7CS6POKbpDQ3yebxDo2dyPPX7gowA&s",
    description: "60% milk, 5% sugar, 20% raspberry jam",
    originalPrice: 0.95,
    stock:12,
    salePrice: 0.65
}]

export const ProductsPage = () => {
    const [disabledButton, setDisabledButton] = useState(true);

    const formik = useFormik({
        initialValues: {
            name: '',
        },
        validationSchema: validationSchema,
        onSubmit: async (values) => {
            console.log("Form Submitted with values:", values);
        }
    });


    return (

        <Box sx={{
            paddingLeft: '10vw',
            paddingRight: '10vw',
            marginTop: '100px',
            marginLeft: '2%',
            marginRight: '2%',
            paddingBottom: "1rem"
        }}>
            <form onSubmit={formik.handleSubmit}>
                <Grid
                    container
                    spacing={2}
                >{/*
                    <Grid item xs={12} md={2} lg={2}>
                        <img src={logo} alt="Logo" style={{height: "7vh"}}/>
                    </Grid>*/}
                    <Grid item xs={12} md={12} lg={12}>
                        <TextField
                            id="name"
                            name="name"
                            fullWidth={true}
                            value={formik.values.name}
                            onChange={(e) => {
                                formik.setFieldValue("name", e.target.value);
                                setDisabledButton(e.target.value === "");
                            }}
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <SearchIcon/>
                                    </InputAdornment>
                                ),
                                endAdornment: (
                                    <Button
                                        type="submit"
                                        disabled={disabledButton}
                                        variant="contained"
                                    >
                                        Search
                                    </Button>
                                ),
                            }}
                            placeholder="Search"
                            error={formik.touched.name && Boolean(formik.errors.name)}
                            helperText={formik.touched.name && formik.errors.name}
                            variant="outlined"
                        />
                    </Grid>
                </Grid>
            </form>
            <Grid
                style={{marginTop: '2rem'}}
                container
                spacing={2}
            >
                {dummyProducts.length > 0 && dummyProducts.map((prod) => {
                    return <Grid item xs={12} md={4} lg={3}>
                        <ProductCard key={prod.id} product={prod}></ProductCard>
                    </Grid>
                })}
            </Grid>

        </Box>
    )

};
