package com.andersenlab.aadamovich.entities.car;

import javax.persistence.*;

@Entity
@DiscriminatorValue("MotorCar")
public class MotorCar extends BaseCar{

    @Column
    @Enumerated(EnumType.STRING)
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
