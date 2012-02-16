package com.porvak.bracket.controller;

import com.porvak.bracket.domain.Pool;
import com.porvak.bracket.domain.UserPicks;
import com.porvak.bracket.repository.PoolRepository;
import com.porvak.bracket.repository.UserPicksRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class PoolController {

    @Inject
    private UserPicksRepository userPicksRepository;
    
    @Inject
    private PoolRepository poolRepository;

    /**
     * Create or update a users pick for a given pool.
     * A user may only have one pick per pool, so if a users pick already exists then
     * the application will overwrite the existing picks.
     *
     * @param poolId
     * @param userId
     * @param userPicks
     */
    @ResponseStatus(CREATED)
    @RequestMapping(value = "/pool/{id}/user/{userId}/picks", method = POST)
    public void saveUserPick(@PathVariable("id") String poolId, @PathVariable("userId") String userId, @RequestBody UserPicks userPicks){
        //TODO: validate that the given user has rights to save this pick
        // validate stuff

        //TODO: check if the user already has made a pick for this pool, if so update it
        //get existing user pick for pool
        userPicksRepository.save(userPicks);
    }


    /**
     * Return a users pick for a specific pool
     *
     * @param poolId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pool/{id}/user/{userId}/picks", method = GET)
    public UserPicks getUserPick(@PathVariable("id")String poolId, @PathVariable("userId") String userId){
        return userPicksRepository.findByUserId(userId);
    }

    /**
     * Retrieve pool information for given pool
     *
     * @param poolId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pool/{id}", method = GET)
    public Pool getPool(@PathVariable("id") String poolId){
        return poolRepository.findOne(poolId);
    }
}
