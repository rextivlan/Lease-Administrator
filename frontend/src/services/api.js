// src/services/api.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Adjust to your backend URL

const api = axios.create({
  baseURL: API_BASE_URL,
});

// Add an interceptor to attach the JWT token from localStorage to every request
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default api;
