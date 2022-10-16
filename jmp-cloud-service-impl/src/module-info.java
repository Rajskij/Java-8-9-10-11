module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    exports service.impl;
    exports exception;
    provides service.Service with service.impl.ServiceImpl;
}