// src/components/Lease/LeaseList.jsx
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';
import '../styles/Lease.css';

const LeaseList = () => {
  const [leases, setLeases] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const fetchLeases = async () => {
    try {
      const response = await api.get('/api/leases');
      setLeases(response.data);
    } catch (err) {
      console.error(err);
      setError('Failed to fetch leases.');
    }
  };

  useEffect(() => {
    fetchLeases();
  }, []);

  const handleDelete = async (id) => {
    if(window.confirm('Are you sure you want to delete this lease?')) {
      try {
        await api.delete(`/api/leases/${id}`);
        fetchLeases(); // refresh list after deletion
      } catch (err) {
        console.error(err);
        setError('Failed to delete lease.');
      }
    }
  };

  return (
    <div className="lease-container">
      <h2>Lease Contracts</h2>
      {error && <p className="error">{error}</p>}
      <button onClick={() => navigate('/lease/new')} className="create-button">Create Lease</button>
      <table className="lease-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Contract Number</th>
            <th>Lessee</th>
            <th>Lessor</th>
            <th>Lease Payment</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {leases.map((lease) => (
            <tr key={lease.id}>
              <td>{lease.id}</td>
              <td>{lease.contractNumber}</td>
              <td>{lease.lessee}</td>
              <td>{lease.lessor}</td>
              <td>{lease.leasePayment}</td>
              <td>
                <button onClick={() => navigate(`/lease/edit/${lease.id}`)}>Edit</button>
                <button onClick={() => handleDelete(lease.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LeaseList;
