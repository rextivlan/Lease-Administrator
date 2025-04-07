// src/components/Lease/LeaseList.jsx
import React, { useEffect, useState } from 'react';
import api from '../services/api';
import '../styles/Lease.css';

const LeaseList = () => {
  const [leases, setLeases] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchLeases = async () => {
      try {
        const response = await api.get('/api/leases');
        setLeases(response.data);
      } catch (err) {
        console.error(err);
        setError('Failed to fetch leases.');
      }
    };

    fetchLeases();
  }, []);

  return (
    <div className="lease-container">
      <h2>Leases Contracts</h2>
      {error && <p className="error">{error}</p>}
      <table className="lease-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Contract Number</th>
            <th>Lessee</th>
            <th>Lessor</th>
            <th>Lease Payment</th>
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
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LeaseList;
