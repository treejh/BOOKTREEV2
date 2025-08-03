import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    vus: 10, // virtual users
    duration: '30s',
};

export default function () {
    http.get('http://host.docker.internal:8080/api/test');
    sleep(1);
}
