create index IX_462AC7AB on StressTool_LinkUsecaseRequest (recordId);
create index IX_587C282A on StressTool_LinkUsecaseRequest (request_id);
create index IX_A5AE18EA on StressTool_LinkUsecaseRequest (request_id, recordId);
create index IX_3C3D8616 on StressTool_LinkUsecaseRequest (request_id, weight);

create index IX_531D7B9B on StressTool_Record (portletId);

create index IX_34F85EB7 on StressTool_Request (parentPlId);
create index IX_7899B483 on StressTool_Request (parentPlId, scenario_id);
create index IX_E7D9A96F on StressTool_Request (parentPlId, scenario_id, weight);
create index IX_258F3998 on StressTool_Request (scenario_id);
create index IX_4F385812 on StressTool_Request (scenario_id, portlet);
create index IX_A55F4DFE on StressTool_Request (scenario_id, portlet, weight);
create index IX_87174984 on StressTool_Request (scenario_id, weight);

create index IX_43512908 on StressTool_Scenario (name);
create index IX_4E9E331A on StressTool_Scenario (simulation_id);
create index IX_C62ADFA4 on StressTool_Scenario (variableName);
create index IX_890D0E1F on StressTool_Scenario (variableName, simulation_id);

create index IX_2117B35B on StressTool_Simulation (variableName);

create index IX_CB06F193 on StressTool_UrlRecord (recordId);