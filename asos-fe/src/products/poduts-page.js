import {Box, Button, Grid, InputAdornment, TextField} from "@mui/material";
import React, {useState} from "react";
import {useFormik} from "formik";
import SearchIcon from '@mui/icons-material/Search';
import * as Yup from "yup";
import logo from "../llgo.png";

const validationSchema = Yup.object({
    name: Yup.string()
        .max(30, 'Name must be at most 30 characters')
        .matches(/^[a-zA-Z0-9áäčďéíĺľňóôŕšťúýžÁÄČĎÉÍĹĽŇÓÔŔŠŤÚÝŽ]+$/, 'Search can only contain letters and numbers'),
});

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

        <Box sx={{paddingLeft: '10vw', paddingRight: '10vw', marginTop: '100px', marginLeft:'40px'}}>
            <form onSubmit={formik.handleSubmit}>
                <Grid
                    container
                    spacing={2}
                >
                    <Grid item xs={12} md={12} lg={1}  sx={{alignItems:'center',
                        justifyContent: 'center', justifySelf:'center', alignSelg:'center'}}>
                        <img src={logo} alt="Logo" style={{height: 45}}/>
                    </Grid>
                    <Grid item xs={12} md={12} lg={10}>
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
                            placeholder="Search accommodation"
                            error={formik.touched.name && Boolean(formik.errors.name)}
                            helperText={formik.touched.name && formik.errors.name}
                            variant="outlined"
                        />
                    </Grid>
                </Grid>
            </form>
        </Box>
)
    ;
};
