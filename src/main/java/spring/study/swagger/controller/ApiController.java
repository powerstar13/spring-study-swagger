package spring.study.swagger.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import spring.study.swagger.dto.UserReq;
import spring.study.swagger.dto.UserRes;

@Api(tags = { "API 정보를 제공하는 Controller" }) // Swagger에 컨트롤러 정보 제공
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "x", value = "x값", required = true, dataTypeClass = int.class, paramType = "path"),
        @ApiImplicitParam(name = "y", value = "y값", required = true, dataTypeClass = int.class, paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int plus(
//        @ApiParam(value = "x값")
        @PathVariable int x,
//        @ApiParam(value = "y값")
        @RequestParam int y
    ) {
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 반환하는 메서드")
    @GetMapping("/user")
    public UserRes user(UserReq userReq) {
        return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq userReq) {
        return new UserRes(userReq.getName(), userReq.getAge());
    }
}
