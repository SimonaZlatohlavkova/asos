import {useState} from "react";
import {getCookie} from "./App";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

export const fetchWithRateLimit = async (body, lastRequestTime, setLastRequestTime, setRequestCount, requestCount, url, navigate) => {
    const maxRequests = 3;
    const requestInterval = 5000

    return new Promise((resolve, reject) => {
        const makeRequest = async () => {

            console.log(body, url)
            const currentTime = new Date().getTime();
            console.log(currentTime)
            console.log(requestInterval)
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
                const response = await fetch("http://localhost:8443/" + url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `${token}`
                    },
                    body: JSON.stringify(body)
                });
                const jsonData = await response.json();

                if (!response.ok) {
                    if (response.status === 401) {
                        navigate("/signin")
                    } else {
                        toast.error(jsonData.message)
                    }
                } else {
                    resolve(response)
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