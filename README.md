# Key-Account-Manager

A Key Account Manager (KAM) is someone responsible for managing and growing relationships with the most important customers of a company

**Requirement** : A Lead Management System for Key Account Managers (KAMs) who manage relationships with large restaurant accounts. This system will help track and manage leads, interactions, and account performance.

## Functionality

### Lead Management
- Add new restaurant leads
- Store basic restaurant information
- Track lead status

### Contact Management
- Multiple Points of Contact (POCs) per restaurant
- Store contact details (name, role, contact information)
- Support multiple POCs with different roles

### Interaction Tracking
- Record all calls made to leads
- Track orders placed
- Store interaction dates and details

### Call Planning
- Set call frequency for each lead
- Display leads requiring calls today
- Track last call made

### Performance Tracking
- Track well-performing accounts
- Monitor ordering patterns and frequency
- Identify underperforming accounts

## APIs

### Lead Management

Adding new Restaurant, Lead and Multiple Point of Contacts.
```
/api/restaurant_lead (POST) 
```
Returns the Status of Restaurant Lead.
```
/api/lead_status?id={id} (GET)
```

## Services


## How to run ?

- Clone it and just let Maven do the magic



