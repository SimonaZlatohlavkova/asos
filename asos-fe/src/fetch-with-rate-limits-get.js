import {useState} from "react";
import {getCookie} from "./App";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

export const fetchWithRateLimitsGET= async (lastRequestTime, setLastRequestTime, setRequestCount, requestCount, url, navigate) => {
    const maxRequests = 3;
    const requestInterval = 5000

    return new Promise((resolve, reject) => {
        const makeRequest = async () => {

            const currentTime = new Date().getTime();
            try {
                if (currentTime - lastRequestTime < requestInterval) {
                    setRequestCount(requestCount + 1);
                    setLastRequestTime(currentTime)
                    if (requestCount > maxRequests) {
                        throw new Error('Too many requests, please try again later.');
                    }
                } else {
                    setRequestCount(1)
                    setLastRequestTime(currentTime)
                }

                const token = getCookie('auth');
                const response = await fetch("http://localhost:8088/" + url, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `${token}`
                    },
                });
                const jsonData = await response.json();

                if (!response.ok) {
                    if (response.status === 401) {
                        toast.error("You are not authenticated")
                        navigate("/signin")
                    } else {
                        toast.error(jsonData.message)
                    }
                } else {
                    resolve(jsonData)
                }
            } catch (error) {
                if (error instanceof Error) {
                    toast.error(error.message);
                } else {
                    const errorMessage = await error.text();
                    const errorObject = JSON.parse(errorMessage);
                    toast.error(errorObject.error);
                }
            }
        };

        makeRequest();
    });
};