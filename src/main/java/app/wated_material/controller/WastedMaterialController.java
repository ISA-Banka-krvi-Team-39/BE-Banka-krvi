package app.wated_material.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "WastedMaterial controller", description = "The wastedMaterial API")
@RestController
@RequestMapping(value = "/api/wastedMaterial")
public class WastedMaterialController {

}
