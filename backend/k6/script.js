import http from 'k6/http';
import { sleep, check } from 'k6';

// ✅ init 단계에서 미리 파일 열기 (전역 스코프)
const file1 = open('/app/test1.png', 'b');
const file2 = open('/app/test2.png', 'b');
const file3 = open('/app/test3.png', 'b');

// 파일 배열
const imageFiles = [
    { name: 'test1.png', content: file1 },
    { name: 'test2.png', content: file2 },
    { name: 'test3.png', content: file3 },
];

// 테스트 옵션
export let options = {
    vus: 100,
    duration: '10s',
};

export default function () {
    // 랜덤 이미지 선택
    const randomIndex = Math.floor(Math.random() * imageFiles.length);
    const image = imageFiles[randomIndex];

    const data = {
        postId: '5',
        'images[0]': http.file(file1, 'test1.png'),
        'images[1]': http.file(file2, 'test2.png'),
        'images[2]': http.file(file3, 'test3.png'),
    };



    const res = http.patch('http://host.docker.internal:8080/api/images/post/update', data);

    check(res, {
        '✅ 상태 코드 200': (r) => r.status === 200,
    });

    sleep(1);
}
