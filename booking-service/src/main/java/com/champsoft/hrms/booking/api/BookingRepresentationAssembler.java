package com.champsoft.hrms.booking.api;

import com.champsoft.hrms.booking.api.BookingResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookingRepresentationAssembler {

    public EntityModel<BookingResponse> toModel(BookingResponse response) {

        EntityModel<BookingResponse> model = EntityModel.of(response);

        model.add(linkTo(methodOn(BookingController.class)
                .get(response.id()))
                .withSelfRel());

        model.add(linkTo(methodOn(BookingController.class)
                .list())
                .withRel("bookings"));

        if ("CREATED".equalsIgnoreCase(response.status())) {

            model.add(
                    linkTo(methodOn(BookingController.class)
                            .cancel(response.id()))
                            .withRel("cancel")
            );
        }

        if ("CONFIRMED".equalsIgnoreCase(response.status())) {

            model.add(
                    linkTo(methodOn(BookingController.class)
                            .cancel(response.id()))
                            .withRel("cancel")
            );
        }

        return model;
    }

    public CollectionModel<EntityModel<BookingResponse>> toCollectionModel(List<BookingResponse> responses) {

        List<EntityModel<BookingResponse>> items = responses.stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(
                items,
                linkTo(methodOn(BookingController.class).list()).withSelfRel()
        );
    }
}