<script>
	import {onMount} from "svelte";
	import {logout} from "./member/login_check.js";
	import {toastNotice} from "../app.js";

	let loginUsername = $state({});
	let isLogin = $state({});

	async function logoutProcess() {
		await logout();
		toastNotice("로그아웃 되었습니다.");

		isLogin = false;
		loginUsername = '';
	}

	onMount(() => {
		if (localStorage.getItem('nickname')){
			loginUsername = localStorage.getItem('nickname');
			isLogin = true;
		} else {
			isLogin = false;
		}
	});
</script>

<svelte:head>
	<title>Main</title>
	<meta name="description" content="Game Dragons" />
</svelte:head>

<section class="flex flex-row w-full">
	<div class="flex flex-row mx-auto px-4 sm:px-6 w-full">
		<div class="flex flex-col w-full lg:py-60 lg:px-60 md:py-32 md:px-32 sm:py-12 sm:px-12">
			<h1 class="mb-5 text-6xl font-bold">
				Game Dragons
			</h1>
			<h1 class="mb-9 text-2xl font-semibold">
				게임 커뮤니티 사이트
			</h1>
		</div>
		<div class="w-full lg:py-60 lg:px-60 md:py-32 md:px-32 sm:py-12 sm:px-12">
			<div class="card bg-neutral text-neutral-content">
				<div class="card-body flex flex-col">
					{#if isLogin}
						<div class="items-start">
							<p>{loginUsername} 님, 환영합니다!</p>
						</div>
						<div class="items-center">
							<div class="card-actions justify-center">
								<a class="btn border-gray-600 btn-ghost w-full" href="/article">글 작성하러 가기</a>
							</div>
						</div>
						<div class="items-end text-end">
							<a class="btn border-gray-600 btn-ghost" on:click={logoutProcess}> <i class="fa-solid fa-door-closed"></i>로그아웃</a>
						</div>
					{:else}
						<div class="items-start">
							<p>로그인 후 이용해보세요!</p>
						</div>
						<div class="items-center">
							<div class="card-actions justify-center">
								<a class="btn border-gray-600 btn-ghost w-full" href="/member/login">Game Dragons 로그인</a>
							</div>
						</div>
						<div class="items-end text-end">
							<a class="btn border-gray-600 btn-ghost" href="/member/signup"> <i class="fa-solid fa-user-plus"></i> 회원가입</a>
						</div>
					{/if}
				</div>
			</div>
		</div>
	</div>
</section>

<style>

</style>
