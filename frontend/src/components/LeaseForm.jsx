// src/components/Lease/LeaseForm.jsx
import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import api from '../services/api';
import '../styles/Lease.css';

const LeaseForm = () => {
  const { id } = useParams();
  const isEditMode = Boolean(id);
  const navigate = useNavigate();

  const [lease, setLease] = useState({
    contractNumber: '',
    leaseStartDate: '',
    leaseEndDate: '',
    leaseType: '',
    lessee: '',
    lessor: '',
    leasePayment: '',
    residualValue: '',
    initialDirectCosts: ''
  });
  const [error, setError] = useState('');

  useEffect(() => {
    if (isEditMode) {
      api.get(`/api/leases/${id}`)
         .then((response) => {
            const data = response.data;
            setLease({
              ...data,
              leasePayment: data.leasePayment ? data.leasePayment.toString() : '',
              residualValue: data.residualValue ? data.residualValue.toString() : '',
              initialDirectCosts: data.initialDirectCosts ? data.initialDirectCosts.toString() : '',
              // Ensure dates are in yyyy-MM-dd format if needed.
              leaseStartDate: data.leaseStartDate,
              leaseEndDate: data.leaseEndDate
            });
         })
         .catch(() => setError('Failed to fetch lease details.'));
    }
  }, [id, isEditMode]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLease((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditMode) {
        await api.put(`/api/leases/${id}`, lease);
      } else {
        await api.post('/api/leases', lease);
      }
      navigate('/leases');
    } catch (err) {
      console.error(err);
      setError('Failed to save lease.');
    }
  };

  return (
    <div className="lease-form-container">
      <h2>{isEditMode ? 'Edit Lease' : 'Create Lease'}</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <label>Contract Number:</label>
        <input type="text" name="contractNumber" value={lease.contractNumber} onChange={handleChange} required />

        <label>Lease Start Date:</label>
        <input type="date" name="leaseStartDate" value={lease.leaseStartDate} onChange={handleChange} required />

        <label>Lease End Date:</label>
        <input type="date" name="leaseEndDate" value={lease.leaseEndDate} onChange={handleChange} required />

        <label>Lease Type:</label>
        <input type="text" name="leaseType" value={lease.leaseType} onChange={handleChange} required />

        <label>Lessee:</label>
        <input type="text" name="lessee" value={lease.lessee} onChange={handleChange} required />

        <label>Lessor:</label>
        <input type="text" name="lessor" value={lease.lessor} onChange={handleChange} required />

        <label>Lease Payment:</label>
        <input type="number" name="leasePayment" value={lease.leasePayment} onChange={handleChange} required />

        <label>Residual Value:</label>
        <input type="number" name="residualValue" value={lease.residualValue} onChange={handleChange} required />

        <label>Initial Direct Costs:</label>
        <input type="number" name="initialDirectCosts" value={lease.initialDirectCosts} onChange={handleChange} required />

        <button type="submit">{isEditMode ? 'Update Lease' : 'Create Lease'}</button>
      </form>
    </div>
  );
};

export default LeaseForm;
