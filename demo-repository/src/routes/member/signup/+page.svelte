<script>
	$: signupData = {
		username: '',
		nickname: '',
		password1: '',
		password2: '',
		email: ''
	};

	async function signup() {
		await fetch(`http://localhost:8090/member/signup`, {
			headers: {
				'Content-Type': 'application/json'
			},
			method: 'POST',
			body: JSON.stringify(signupData)
		});
	}
</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section>
	<h1>유저 회원가입</h1>
	<form>
		<div>
			<label for="username">사용자ID</label>
			<input
				type="text"
				class="textarea textarea-bordered"
				placeholder="username"
				bind:value={signupData.username}
			/>
		</div>
		<div>
			<label for="nickname">닉네임</label>
			<input
				type="text"
				class="textarea textarea-bordered"
				placeholder="nickname"
				bind:value={signupData.nickname}
			/>
		</div>
		<div>
			<label for="password1">비밀번호</label>
			<input
				type="password"
				class="textarea textarea-bordered"
				placeholder="password"
				bind:value={signupData.password1}
			/>
		</div>
		<div>
			<!-- // 비밀번호 확인하는 과정 추가 필요 -->
			<label for="password2">비밀번호 확인</label>
			<input
				type="password"
				class="textarea textarea-bordered"
				placeholder="password confirm"
				bind:value={signupData.password2}
			/>
		</div>
		<div>
			<label for="email">이메일</label>
			<input
				type="email"
				class="textarea textarea-bordered"
				placeholder="email"
				bind:value={signupData.email}
			/>
		</div>
		<button type="submit" on:click={(event) => signup()}>회원가입</button>
	</form>
</section>

<style>
</style>
