package com.porvak.bracket.domain;

import com.google.common.collect.Maps;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.*;

public class UserPick extends AbstractBracket{

    @JsonProperty
    private int regionId;

    @JsonProperty
    private int gameId;

    @JsonProperty
    private String teamId;
    
    @JsonProperty
    private int positionId;
    
    @Transient
    private Map<String, Integer> userPickKey;

    @PersistenceConstructor
    public UserPick(@JsonProperty("regionId") int regionId, @JsonProperty("gameId") int gameId, @JsonProperty("teamId") String teamId, @JsonProperty("positionId") int positionId) {
        init(regionId, gameId, teamId, positionId);
    }

    private void init(int regionId, int gameId, String teamId, int positionId) {
        this.regionId = regionId;
        this.gameId = gameId;
        this.teamId = teamId;
        this.positionId = positionId;

        userPickKey = Maps.newHashMap();
        userPickKey.put("regionId", regionId);
        userPickKey.put("gameId", gameId);
    }

    /**
     * Create a userPick from a map.
     * 
     * @param userPickMap
     */
    public UserPick(Map<String, Object> userPickMap) {

        Object userPickObj = userPickMap.get("userPick");
        LinkedHashMap userPick = (LinkedHashMap<String, Object>)userPickObj;

        String name = checkNotNull(userPick.get("name").toString());
        String teamId = checkNotNull(userPick.get("id").toString());  //TODO change this to teamId
        String seed = checkNotNull(userPick.get("seed").toString());

        int regionId = Integer.valueOf(userPick.get("regionId").toString());
        int gameId = Integer.valueOf(checkNotNull(userPickMap.get("gameId")).toString());
        int positionId = Integer.valueOf(checkNotNull(userPickMap.get("position")).toString());
        init(regionId, gameId, teamId, positionId);
    }

    @JsonIgnore
    public Map<String, Integer> getKey(){
        return userPickKey;
    }

    public int getRegionId() {
        return regionId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getTeamId() {
        return teamId;
    }

    public int getPositionId() {
        return positionId;
    }
}
