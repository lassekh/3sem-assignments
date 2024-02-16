# Assignment Readme Guidelines

## Overview

- Name: Lasse Kjær Hauerberg
- Mail: cph-lh225@cphbusiness.dk

OBS: ignore package class_exercises, just contains exercises from class activity

I have used the same HibernateConfig.java for alle exercises. Find it here: X or loacted in the task1 package. 

### task1

**Exercise link:** https://github.com/dat3Cph/material/blob/sem2024spring/flowJPA/week1/UnicornExercise.md#exercise-java-jpa-with-hibernate---standard-crud-operations-on-unicorn-entity

All solutons can be found in the task1 package.

### task2

**Exercise link:** https://github.com/dat3Cph/material/blob/sem2024spring/flowJPA/week1/JpaLifecycleAndAnnotationExercise.md#jpa-lifecycle-and-annotations

All tasks can be found in package task2.

### task3

**Exercise link:** https://github.com/dat3Cph/material/blob/sem2024spring/flowJPA/week1/PointsExercise.md#the-point-exercise

Solutions can be found in package task3, note that part of the solution is located in the **test** directory.

### task4

**Exercise link:** https://github.com/dat3Cph/material/blob/sem2024spring/flowJPA/week1/LombokExercise.md#coding-exercise-demonstrate-lombok-in-java

Solutions can be found in package task4.

### task5

**Exercise link:** https://github.com/dat3Cph/material/blob/sem2024spring/flowJPA/week1/DAOExercise.md#dao-exercise

Solutions can be found in package task5. This exercise uses the existing code from task2 and is also working with Student class.

### task6

**Exercise link:** https://github.com/dat3Cph/material/blob/sem2024spring/flowJPA/week1/GLSExercise.md#gls-package-delivery-exercise

Solutions can be found in package task6.

## Friday presentation
Presentation about EntityManager and EntityManagerFactory

An EntityManagerFactory is used to create and manage EntityManager instances. We use it to initialize JPA providers, that implements JPA. In our case we use Hibernate and configure how we want to connect to the database.

EntityManager provides a layer between the database and the application. It helps us manage entities lifecycle, perform CRUD operation to the DB and let us perform database operations with an OOP mindset.

**Transient** when not yet associated with EntityManager
**Managed** when associated with an EntityManager and are associated with synchronized data
**Detached** when entity is no longer associated with DB, but has been

Persist() should be used when mapping new object to the DB. Merge() should be used when updating existing objects/rows in DB.
