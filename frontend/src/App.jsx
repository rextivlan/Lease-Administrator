// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './components/Login';
import Register from './components/Register';
import LeaseList from './components/LeaseList';
import LeaseForm from './components/LeaseForm';
import './App.css';

const App = () => {
  return (
    <Router>
      <Navbar />
      <div className="content">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/leases" element={<LeaseList />} />
          <Route path="/lease/new" element={<LeaseForm />} />
          <Route path="/lease/edit/:id" element={<LeaseForm />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
