package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController
{
    @Autowired
    CountryRepository countryrepos;

    // population total
    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> findLargerPopulations()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        long total = 0;

        for (Country c : myList)
        {
            total = total + c.getPopulation();
        }

        System.out.println("The total population is: " + total);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // population min
    @GetMapping(value = "/population/min", produces = {"application/json"})

    public ResponseEntity<?> findMinPopulation()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));

        Country rtnCountry = myList.get(0);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // population max
    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> findMaxPopulation()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int) (c2.getPopulation() - c1.getPopulation()));

        Country rtnCountry = myList.get(0);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // population median
    @GetMapping(value = "/population/median", produces = {"application/json"})
    public ResponseEntity<?> findMedianPopulation()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));

        Country rtnCountry = myList.get((myList.size() / 2) + 1);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // all countries
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);

        myList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
}
