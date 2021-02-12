package com.lambdaschool.countrysearch.controllers;


import com.lambdaschool.countrysearch.models.Country;
import com.lambdaschool.countrysearch.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.toUpperCase;
@RestController
public class CountryController {

    @Autowired
    CountryRepository cntryrepos;

    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries() {
        List<Country> myList = new ArrayList<>();
        cntryrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> findByLetter(@PathVariable char letter) {
        List<Country> rtnList = new ArrayList<>();
        for (Country c : cntryrepos.findAll()) {
            if (c.getName().charAt(0) == toUpperCase(letter))
            {
                rtnList.add(c);
            }
        }
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> sumTotalPopulation() {
        long totalPop = 0;
        for (Country c : cntryrepos.findAll()) {
            totalPop += c.getPopulation();
        }
        System.out.println("The Total Population is " + totalPop);
        return new ResponseEntity<>("The Total Population is " + totalPop, HttpStatus.OK);
    }

    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> findMaxPopulation()
    {
        Country maxPop = new Country();
        for (Country c : cntryrepos.findAll())
        {
            if(c.getPopulation() > maxPop.getPopulation())
            {
                maxPop = c;
            }
        }
        return new ResponseEntity<>(maxPop, HttpStatus.OK);
    }
    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> findMinPopulation() {
        Country minPop = new Country();
        for (Country c : cntryrepos.findAll()) {
            if(c.getPopulation() < minPop.getPopulation()) {
                minPop = c;
            }
            if (minPop.getPopulation() == 0) {
                minPop = c;
            }
        }
        return new ResponseEntity<>(minPop, HttpStatus.OK);
    }
    @GetMapping(value = "/population/median", produces = {"application/json"})
    public ResponseEntity<?> findMedianPopulation() {
        List<Country> sortedPop = new ArrayList<>();
        cntryrepos.findAll().iterator().forEachRemaining(sortedPop::add);
        sortedPop.sort((c1, c2) -> (int) c1.getPopulation() - c2.getPopulation());
        return new ResponseEntity<>(sortedPop.get(sortedPop.size() / 2), HttpStatus.OK);
    }
}






