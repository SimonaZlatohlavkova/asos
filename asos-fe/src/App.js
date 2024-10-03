import {RegistrationForm} from "./registration/registration-form";
import Navbar from "./navbar/navbar";
import React, {useState} from 'react';
import {BrowserRouter as Router, Navigate, Route, Routes} from 'react-router-dom';
import {SignInForm} from "./signin/sign-in-form";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {Container, createTheme, ThemeProvider} from "@mui/material";
import {green} from "@mui/material/colors";
import {CartPage} from "./cart/cart";
import {ProductsPage} from "./products/poduts-page";
import {Profile} from "./profile/profile";



const theme = createTheme({
    palette: {
        primary: {
            main: green[500],
        },
        secondary: {
            main: green[900],
        },
    },
    components: {
        MuiButton: {
            styleOverrides: {
                root: {
                    borderRadius: '5px',
                    color: "white",
                },
            },
        },
        MuiOutlinedInput: {
            styleOverrides: {
                root: {
                    '& .MuiOutlinedInput-notchedOutline': {
                        borderRadius: '5px', // Set the border radius here
                    },
                },
            },
        },
    },
});

export function setCookie(name, value, expirationInMinutes) {
    const expirationDate = new Date();
    expirationDate.setTime(expirationDate.getTime() + (expirationInMinutes * 60 * 1000));
    const expires = "expires=" + expirationDate.toUTCString();
    document.cookie = name + "=" + encodeURIComponent(value) + ";" + expires + ";path=/";
}


export function getCookie(name) {
    const cookies = document.cookie.split(';');

    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith(name + '=')) {
            return decodeURIComponent(cookie.substring(name.length + 1));
        }
    }

    return null;
}

export function removeCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
}


function App() {

    return (
        <div className="App">
            <ThemeProvider theme={theme}>
                <ToastContainer/>
                    <Router>
                        <Navbar/>
                        <Routes>
                            <Route exact path="/registration" element={<RegistrationForm></RegistrationForm>}></Route>
                            <Route exact path="/signin" element={<SignInForm></SignInForm>}></Route>
                            <Route exact path="/cart" element={<CartPage></CartPage>}></Route>
                            <Route exact path="/products/search" element={<ProductsPage></ProductsPage>}/>
                            <Route path="/profile" element={<Profile></Profile>}/>
                            <Route path="/" element={<Navigate to="/home"/>}/>
                        </Routes>
                    </Router>
            </ThemeProvider>
        </div>
    );
}

export default App;
