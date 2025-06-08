'use client'

import { useState } from 'react'
import { useGlobalLoginUser } from '@/stores/auth/loginMember'
import { useRouter, usePathname } from 'next/navigation' // usePathname 추가
import Link from 'next/link'

export default function Header() {
    const router = useRouter()
    const pathname = usePathname() // 현재 경로 가져오기
    const { isLogin, loginUser, logoutAndHome } = useGlobalLoginUser()

    const [keyword, setKeyword] = useState('')
    const [field, setField] = useState<'all' | 'title' | 'book' | 'author'>('all')

    const onSearch = () => {
        if (!keyword.trim()) return
        router.push(`/search?type=${field}&q=${encodeURIComponent(keyword)}`)
    }

    const onKeyDown = (e: React.KeyboardEvent) => {
        if (e.key === 'Enter') onSearch()
    }

    // 특정 경로에서 검색창 숨기기
    const hideSearchBar = pathname.startsWith('/account') || pathname.startsWith('/mypage')

    return (
        <header className="fixed top-0 left-0 right-0 z-50 border-b border-gray-200 bg-white">
            <div className="w-full px-1 py-3 flex items-center justify-between">
                <Link href="/" className="flex items-center pl-5 cursor-pointer">
                    <img
                        src="https://booktree-s3-bucket.s3.ap-northeast-2.amazonaws.com/BookTreeLogo.png"
                        alt="책 아이콘"
                        className="w-9 h-9 mr-2"
                    />
                    <h1 className="text-xl font-bold">BookTree</h1>
                </Link>

                {/* 검색창 */}
                {!hideSearchBar && ( // 검색창 조건부 렌더링
                    <div className="flex items-center space-x-2">
                        <div className="relative flex items-center border border-gray-200 rounded-md overflow-hidden">
                            <select
                                value={field}
                                onChange={(e) => setField(e.target.value as any)}
                                className="appearance-none bg-white pl-4 pr-9 py-2 text-sm outline-none cursor-pointer"
                            >
                                <option value="all">전체</option>
                                <option value="title">제목</option>
                                <option value="book">책</option>
                                <option value="author">작성자</option>
                            </select>
                            <div className="absolute right-3 pointer-events-none">
                                <svg width="10" height="6" viewBox="0 0 10 6" fill="none">
                                    <path
                                        d="M1 1L5 5L9 1"
                                        stroke="#666"
                                        strokeWidth="1.5"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    />
                                </svg>
                            </div>
                        </div>
                        <div className="flex items-center border border-gray-200 rounded-md overflow-hidden">
                            <input
                                value={keyword}
                                onChange={(e) => setKeyword(e.target.value)}
                                onKeyDown={onKeyDown}
                                type="text"
                                placeholder="게시물을 검색하기"
                                className="px-4 py-2 w-64 focus:outline-none text-sm"
                            />
                            <button onClick={onSearch} className="px-3 bg-white hover:bg-gray-50">
                                <svg
                                    className="w-5 h-5 text-gray-500"
                                    fill="none"
                                    stroke="currentColor"
                                    viewBox="0 0 24 24"
                                >
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        strokeWidth="2"
                                        d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                                    />
                                </svg>
                            </button>
                        </div>
                    </div>
                )}

                {/* 로그인/아웃 */}
                {isLogin ? (
                    <div className="flex items-center pr-5">
                        <div className="flex items-center space-x-1">
                            <Link
                                href={`/mypage/${loginUser.id}`}
                                className="w-8 h-8 rounded-full bg-gray-200 overflow-hidden"
                            >
                                <img
                                    src={
                                        loginUser.image ||
                                        'https://booktree-s3-bucket.s3.ap-northeast-2.amazonaws.com/%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%8C%E1%85%A1%E1%84%80%E1%85%B5%E1%84%87%E1%85%A9%E1%86%AB%E1%84%8B%E1%85%B5%E1%84%86%E1%85%B5%E1%84%8C%E1%85%B5.png'
                                    }
                                    alt="프로필 이미지"
                                    className="w-full h-full object-cover"
                                />
                            </Link>
                            <Link href={`/mypage/${loginUser.id}`} className="text-sm hover:underline">
                                {loginUser.username}님
                            </Link>
                        </div>
                        <button
                            onClick={logoutAndHome}
                            className="ml-4 px-4 py-1 text-sm text-white bg-red-500 rounded hover:bg-red-600"
                        >
                            로그아웃
                        </button>
                    </div>
                ) : (
                    <div className="flex items-center pr-5 space-x-2">
                        <button
                            onClick={() => router.push('/account/login')}
                            className="px-4 py-2 text-sm border border-gray-200 rounded-md hover:bg-gray-50"
                        >
                            로그인
                        </button>
                        <button
                            onClick={() => router.push('/account/signup')}
                            className="px-4 py-2 text-sm text-white bg-[#2E804E] rounded-md hover:bg-[#236b3e]"
                        >
                            회원가입
                        </button>
                    </div>
                )}
            </div>
        </header>
    )
}
