package com.example.poc.entity;
import java.time.LocalDateTime;
import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@ApiModel(description = "Sample Ticketing Model")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets_v")
public class Ticket {
    @Id
    @Column(name="ticket_no")
    private String ticketNumber;
    @Column(name="passenger_name")
    private String passengerName;
    @Column(name="fare_conditions")
    private String fareConditions;
    private Float amount;
    @Column(name="flight_id")
    private Integer flightId;
    @Column(name="flight_no")
    private String flightNo;
    @Column(name="scheduled_departure")
    private LocalDateTime scheduledDeparture;
    @Column(name="scheduled_departure_local")
    private LocalDateTime scheduledDepartureLocal;
    @Column(name="scheduled_arrival")
    private LocalDateTime scheduledArrival;
    @Column(name="scheduled_arrival_local")
    private LocalDateTime scheduledArrivalLocal;
    @Column(name="departure_airport")
    private String departureAirport;
    @Column(name="departure_airport_name")
    private String departureAirportName;
    @Column(name="departure_city")
    private String departureCity;
    @Column(name="arrival_airport")
    private String arrivalAirport;
    @Column(name="arrival_airport_name")
    private String arrivalAirportName;
    @Column(name="arrival_city")
    private String arrivalCity;
    private String status;
    @Column(name="aircraft_code")
    private String aircraftCode;
    @Column(name="actual_departure")
    private LocalDateTime actualDeparture;
    @Column(name="actual_departure_local")
    private LocalDateTime actualDepartureLocal;
    @Column(name="actual_arrival")
    private LocalDateTime actualArrival;
    @Column(name="actual_arrival_local")
    private LocalDateTime actualArrivalLocal;
}