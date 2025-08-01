package io.devotel.soap;

import io.devotel.soap.objects.GetUserByIdRequest;
import io.devotel.soap.objects.GetUserByIdResponse;
import io.devotel.soap.objects.User;
import io.devotel.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@RequiredArgsConstructor
@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://devotel.io/userservice";

    private final UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) {
        GetUserByIdResponse response = new GetUserByIdResponse();
        ModelMapper modelMapper = new ModelMapper();
        response.setUser(modelMapper.map(userService.getUserById(request.getId()), User.class));
        return response;
    }
}
