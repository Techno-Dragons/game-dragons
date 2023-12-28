<script>

</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section class="flex flex-row w-full">
	<div class="flex flex-row mx-auto px-4 sm:px-6 w-full">
		<div class="flex flex-col w-full py-60 px-60">
			<h1 class="mb-5 text-6xl font-bold text-white">
				Game Dragons
			</h1>
			<h1 class="mb-9 text-2xl font-semibold text-gray-200">
				게임 커뮤니티 사이트
			</h1>
		</div>
		<div class="w-full py-60 px-60">
			<div class="card bg-neutral text-neutral-content">
				<div class="card-body flex flex-col">
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
				</div>
			</div>
		</div>
	</div>
</section>

<style>

</style>
