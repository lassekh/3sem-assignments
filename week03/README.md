# Assignment Readme Guidelines

## Overview

- Name: Lasse Kjær Hauerberg
- Mail: cph-lh225@cphbusiness.dk


## Friday presentation
Presentation about EntityManager and EntityManagerFactory

An EntityManagerFactory is used to create and manage EntityManager instances. We use it to initialize JPA providers, that implements JPA. In our case we use Hibernate and configure how we want to connect to the database.

EntityManager provides a layer between the database and the application. It helps us manage entities lifecycle, perform CRUD operation to the DB and let us perform database operations with an OOP mindset.

**Transient** when not yet associated with EntityManager
**Managed** when associated with an EntityManager and are associated with synchronized data
**Detached** when entity is no longer associated with DB, but has been

Persist() should be used when mapping new object to the DB. Merge() should be used when updating existing objects/rows in DB.
