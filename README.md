# Late Sleeper
An android application to help you break the habit of going to sleep late.  

# Table of Contents
- [Functionalities](#functionalities)
  * [Welcome Screen](#welcome-screen)
  * [Log In](#log-in)
  * [Create Account](#create-account)
- [Firestore](#firestore)
  * [Initialize Cloud Firestore Database](#initialize-cloud-firestore-database)
- [Documents](#documents)
  * [User](#user)
  * [Entry](#entry)
- [Collections](#collections)
- [ImplementationOfthe4Steps](#four-steps)
  * [Relabel](#relabel)
  * [Reframe](#reframe)
  * [Refocus](#refocus)
  * [Revalue](#revalue)

# Functionalities

## Welcome Screen
## Log In
## Create Account

# Firestore

## Initialize Cloud Firestore Database
To get an instance of our entire firestore database, use the following command:
```
// Access a Cloud Firestore instance from any Activity
FirebaseFirestore lateSleeperDB = FirebaseFirestore.getInstance();
```

# Documents

## User
This document will contain data about the users. The data model is as follows:
```
user = {
  userID: String
  username: String
  password: String
  email: String
  firstName: String
  lastName: String
  address: String
  city: String
  province: String
  postalCode: String
  phoneNumber: String
}
```

## Entry
The entry document will store a journal entry that users write throughout their time using the application. The data model is as follows:
```
entry = {
  title: String
  body: String
  date: String
}
```

# Collections
So far, we have a users collection that stores all of the users of our application. Journal will be a sub-collection of a user. Each user will have a journal collection that stores entries. Here is the hierarchy as follows: 
```
users
  -> user document
    -> journal
      -> entry document
      -> entry document
      -> entry document
  -> user document
    -> journal
  -> user document
    -> journal
```
# Relabel
At this Step, the user is directed to imput how they are feeling. This is done to remind the user of how they are feeling at the moment.

# Reframe
At this step, the user is reminded to be aware of bad thinking and to try to reason with the "wise advocate".
Also, Change your perception of the importance of the deceptive brain messages; say why these thoughts, urges, and impulses keep bother you (it's not me, it's just the brain!).

Consult with wise advocate, if you determine pattern is not harmful then keep it, else call it what it is(false alarm, its not me its my brain's wiring)

# Refocus
At this step, the user is directed to shift their attention to the direction they want to go, and do something constructive as what one has control over is their actions.
In other words, Direct your attention toward an activity or mental process that is wholesome and productive—even while the false and deceptive urges, thoughts, impulses, and sensations are still present and bothering you.
Choose not to follow the harmful action but foctus on an alternative thats more constructive.

# Revalue
At this step, the user will be directed to make a journal entry to reflect on how the pattern of behaviour affected their life negatively.
In other words, clearly see the pattern for what it is- something of no value and are deceptive brain messages



