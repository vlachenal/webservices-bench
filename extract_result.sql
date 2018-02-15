SELECT row_to_json(r, true)
FROM (
    SELECT
        s.id,
        s.client_cpu,
        s.client_memory,
        s.client_jvm_version,
        s.client_jvm_vendor,
        s.client_os_name,
        s.client_os_version,
        s.server_cpu,
        s.server_memory,
        s.server_jvm_version,
        s.server_jvm_vendor,
        s.server_os_name,
        s.server_os_version,
        s.protocol,
        s.compression,
        s.nb_threads,
        s.comment,
        json_agg(c) AS components
    FROM TestSuite s
    INNER JOIN TestCall c ON (c.test_suite_id = s.id)
    GROUP BY s.id
) r(id,
client_cpu,
client_memory,
client_jvm_version,
client_jvm_vendor,
client_os_name,
client_os_version,
server_cpu,
server_memory,
server_jvm_version,
server_jvm_vendor,
server_os_name,
server_os_version,
protocol,
compression,
nb_threads,
comment,
component)
