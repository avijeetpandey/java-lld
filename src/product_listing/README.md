# Product listing

We need to develop a property listing service along with the following functionalities
    - User can list property
    - Find property based on requrements
        - price range
        - Number of rooms
    - Sort by ( Rooms and price )
    - User can mark property as Sold , Shortlisted


Creating a system should be High throughput and consistent writes as many people will search and sort at the same time sold operations would be performed we cannot just lock the entire database while the search or sort operation is being performed.