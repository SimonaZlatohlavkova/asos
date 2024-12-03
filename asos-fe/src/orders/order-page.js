import React, {useEffect, useState} from 'react';

import {Orders} from "./orders";
import {updateCartItems} from "../cart/CartRedux";
import {dummyProducts} from "../products/poduts-page";
import {fetchWithRateLimitsGET} from "../fetch-with-rate-limits-get";
import {useNavigate} from "react-router-dom";
import {fetchWithRateLimit} from "../fetch-with-rate-limits";
import {Box, Typography} from "@mui/material";
import {getCookie} from "../App";


const dummyorders = [
    {
        "id": 1,
        "date": "2024-11-01",
        "summarization": 45.0,
        "deliveryCost": 5.0,
        "address": {
            "street": "Main Street",
            "houseNumber": "123A",
            "postCode": "12345",
            "city": "Springfield",
            "country": "USA"
        },
        "status": "completed",
        "products": [
            {
                "id": 1,
                "price": 10.0,
                "quantity": 2,
                "name": "Milk",
                "url": "https://res.cloudinary.com/riqra/image/upload/w_656,h_656,c_limit,q_auto,f_auto/v1695769979/sellers/9/rlzngadefxo9zlc0zigq.webp",

            },
            {
                "id": 2,
                "price": 5.0,
                "quantity": 5,
                "name": "Bread",
                "url": "https://res.cloudinary.com/riqra/image/upload/w_656,h_656,c_limit,q_auto,f_auto/v1695769979/sellers/9/rlzngadefxo9zlc0zigq.webp",

            }
        ]
    },
    {
        "id": 2,
        "date": "2024-11-05",
        "summarization": 120.0,
        "deliveryCost": 10.0,
        "address": {
            "street": "Elm Avenue",
            "houseNumber": "456B",
            "postCode": "67890",
            "city": "Shelbyville",
            "country": "USA"
        },
        "status": "pending",
        "products": [
            {
                "id": 3,
                "price": 30.0,
                "quantity": 2,
                "name": "Cheese",
                "url": "https://via.placeholder.com/150"
            },
            {
                "id": 4,
                "price": 20.0,
                "quantity": 3,
                "name": "Butter",
                "url": "https://via.placeholder.com/150"
            }
        ]
    },
    {
        "id": 3,
        "date": "2024-11-10",
        "summarization": 200.0,
        "deliveryCost": 15.0,
        "address": {
            "street": "Oak Drive",
            "houseNumber": "789C",
            "postCode": "54321",
            "city": "Capital City",
            "country": "USA"
        },
        "status": "shipped",
        "products": [
            {
                "id": 5,
                "price": 50.0,
                "quantity": 2,
                "name": "Steak",
                "url": "https://via.placeholder.com/150"
            },
            {
                "id": 6,
                "price": 25.0,
                "quantity": 4,
                "name": "Wine",
                "url": "https://via.placeholder.com/150"
            }
        ]
    }
]


export const OrderPage = () => {
    const [orders, setOrders] = useState([]);
    const [requestCount, setRequestCount] = useState(0)
    const [lastRequestTime, setLastRequestTime] = useState(0)
    const navigate = useNavigate()

    const token = getCookie('auth');
    useEffect(() => {
        if (token == null) {
            navigate("/home")
        }
    }, []);
    useEffect(() => {
        fetchData()
    }, []);

    const fetchData = async () => {
        const jsonData = await fetchWithRateLimitsGET(lastRequestTime, setLastRequestTime, setRequestCount, requestCount, "api/orders", navigate)
        console.log(jsonData)
        if (jsonData) {
            setOrders(jsonData)
        }
    }

    return (

        <>
            {orders.length === 0 ?
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                sx={{
                    height: '100%', // Adjust height to fully center content in the card
                    textAlign: 'center',
                }}
            ><Typography
                variant="h4"
                color="gray"
                sx={{
                    padding: '200px', // Adds padding for a cleaner look
                }}
            >
                You have no orders
            </Typography> </Box> : <Orders orders={orders}/>}
        </>
    )

};


