package com.rosehulman.edu.Interface;

import com.rosehulman.edu.Utils.Constants;

/**
 * Created by mot on 1/31/17.
 */

public interface ObjectStateOwner extends Temporal {
    void setState(Constants.GameObjectState state);
    Constants.GameObjectState getState();
    void onSetToInactiveState();
    void onSetToActiveState();
    void onSetToDeadState();
    void onSetToRemovableState();
    void onSetToCleaningPhysicsBodyState();
}
