import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 100 },
        { duration: '10s', target: 300 },
        { duration: '10s', target: 500 },
        { duration: '10s', target: 1000 },
        { duration: '10s', target: 0 }, // 종료
    ],
};

export function setup() {
    const loginRes = http.post('http://host.docker.internal:8080/api/v1/users/login', JSON.stringify({
        email: 'test@gmail.com',
        password: 'test1234@@',
    }), {
        headers: { 'Content-Type': 'application/json' }
    });

    check(loginRes, {
        '✅ 로그인 성공': (res) => res.status === 200,
    });

    // ✅ 헤더에서 Authorization 추출
    const rawAuthHeader = loginRes.headers['Authorization'] || loginRes.headers['authorization'];
    const token = rawAuthHeader?.replace('Bearer ', '');

    check(token, {
        '✅ 토큰 포함 (헤더 기반)': (t) => t !== undefined && t.length > 10,
    });

    return { token };
}

export default function (data) {
    const page = Math.floor(Math.random() * 3) + 1;
    const url = `http://host.docker.internal:8080/api/v1/posts/get/likePost?page=${page}&size=8`;

    const res = http.get(url, {
        headers: {
            Authorization: `Bearer ${data.token}`
        }
    });

    check(res, {
        '✅ 조회 응답 성공': (r) => r.status === 200,
        '✅ 응답에 content 포함': (r) => r.body.includes('content'),
    });

    sleep(1);
}
