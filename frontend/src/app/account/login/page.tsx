'use client'

import { useState } from 'react'
import styles from './login.module.css'
import Link from 'next/link'

import { useRouter } from 'next/navigation'

export default function LoginPage() {
    const [rememberLogin, setRememberLogin] = useState(false)
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const socialLoginForKakaoUrl = `${process.env.NEXT_PUBLIC_API_BASE_URL}/oauth2/authorization/kakao`
    const socialLoginForGithubUrl = `${process.env.NEXT_PUBLIC_API_BASE_URL}/oauth2/authorization/github`
    const redirectUrlAfterSocialLogin = `${process.env.NEXT_PUBLIC_FRONT_BASE_URL}`
    const router = useRouter()
    const [showAlert, setShowAlert] = useState(false) // 알림 모달 상태

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()

        const loginData = {
            email,
            password,
        }

        try {
            const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/v1/users/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(loginData),
            })

            if (response.ok) {
                // 로그인 성공 후 리다이렉트
                window.location.href = '/'
            } else {
                console.error('로그인 실패:', response.status)
                setShowAlert(true) // 로그인 실패 시 알림 모달 표시
            }
        } catch (error) {
            console.error('로그인 요청 중 오류 발생:', error)
            setShowAlert(true) // 로그인 실패 시 알림 모달 표시
        }
    }

    return (
        <div className={styles.container}>
            <div className={styles.mainContent}>
                <div className={styles.formContainer}>
                    <h1 className="text-2xl font-bold text-center mb-8">로그인</h1>

                    <form className={styles.form} onSubmit={handleSubmit}>
                        <div className="space-y-6">
                            <div>
                                <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                                    이메일
                                </label>
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
                                    placeholder="이메일을 입력하세요"
                                />
                            </div>

                            <div>
                                <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                                    비밀번호
                                </label>
                                <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-green-500 focus:border-green-500"
                                    placeholder="비밀번호를 입력하세요"
                                />
                            </div>

                            <div className="flex items-center justify-between">
                                <div className="text-sm">
                                    <Link href="/account/findAccount/findId" className="text-gray-700">
                                        이메일 찾기
                                    </Link>
                                    /
                                    <Link href="/account/findAccount/findPassword" className="text-gray-700">
                                        비밀번호 찾기
                                    </Link>
                                </div>
                            </div>

                            <button
                                type="submit"
                                className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-[#2E804E] hover:bg-[#256d41] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-[#2E804E]"
                            >
                                로그인
                            </button>

                            <Link
                                href={`${socialLoginForKakaoUrl}?redirectUrl=${redirectUrlAfterSocialLogin}`}
                                className="w-full flex justify-center items-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-gray-700 bg-[#FFE812] hover:bg-[#FFE200] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-yellow-500"
                            >
                                <svg viewBox="0 0 24 24" className="w-5 h-5 mr-2 fill-current">
                                    <path d="M12 3C6.5 3 2 6.5 2 11c0 2.5 1.2 4.7 3 6.2l-1 3.8 4-2.4c1.3.4 2.6.6 4 .6 5.5 0 10-3.5 10-8s-4.5-8-10-8z" />
                                </svg>
                                카카오로 시작하기
                            </Link>
                            <Link
                                href={`${socialLoginForGithubUrl}?redirectUrl=${redirectUrlAfterSocialLogin}`}
                                className="w-full flex justify-center items-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-[#24292F] hover:bg-[#1C2024] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500"
                            >
                                <svg viewBox="0 0 24 24" className="w-5 h-5 mr-2 fill-current">
                                    <path d="M12 0C5.37 0 0 5.37 0 12c0 5.31 3.435 9.795 8.205 11.385.6.105.825-.255.825-.57 0-.285-.015-1.23-.015-2.235-3.015.555-3.795-.735-4.035-1.41-.135-.345-.72-1.41-1.23-1.695-.42-.225-1.02-.78-.015-.795.945-.015 1.62.87 1.845 1.23 1.08 1.815 2.805 1.305 3.495.99.105-.78.42-1.305.765-1.605-2.67-.3-5.46-1.335-5.46-5.925 0-1.305.465-2.385 1.23-3.225-.12-.3-.54-1.53.12-3.18 0 0 1.005-.315 3.3 1.23.96-.27 1.98-.405 3-.405s2.04.135 3 .405c2.295-1.56 3.3-1.23 3.3-1.23.66 1.65.24 2.88.12 3.18.765.84 1.23 1.905 1.23 3.225 0 4.605-2.805 5.625-5.475 5.925.435.375.81 1.095.81 2.22 0 1.605-.015 2.895-.015 3.3 0 .315.225.69.825.57A12.02 12.02 0 0024 12c0-6.63-5.37-12-12-12z" />
                                </svg>
                                GitHub으로 시작하기
                            </Link>

                            <div className="relative py-4">
                                <div className="absolute inset-0 flex items-center">
                                    <div className="w-full border-t border-gray-200"></div>
                                </div>
                                <div className="relative flex justify-center">
                                    <span className="px-4 bg-white text-sm text-gray-500">또는</span>
                                </div>
                            </div>

                            <div className="text-center text-sm">
                                <span className="text-gray-500">계정이 없으신가요? </span>
                                <Link href="/account/signup" className="text-gray-900 hover:text-gray-700 font-medium">
                                    회원가입
                                </Link>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            {/* 알림 모달 */}
            {showAlert && (
                <div className={styles.alertOverlay}>
                    <div className={styles.alertModal}>
                        <p>아이디, 비밀번호를 확인해주세요.</p>
                        <button
                            type="button"
                            onClick={() => setShowAlert(false)} // 알림 닫기
                            className={styles.alertButton}
                        >
                            확인
                        </button>
                    </div>
                </div>
            )}

            <footer className={styles.footer}>
                <div className={styles.footerDivider}></div>
                <div className={styles.footerLine}></div>
                <div className={styles.footerContent}></div>
                <div className={styles.footerText}>
                    <span className={styles.copyright}>© 2025 All rights reserved.</span>
                </div>
            </footer>
        </div>
    )
}
