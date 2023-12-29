<script>

let inputTitle = $state();
let inputContent = $state();
import axios from 'axios';

async function postArticle() {
		const res = await axios.post(
			`http://localhost:8090/article`,
			{ title: inputTitle, content: inputContent },
			{ withCredentials: true }
		);
		console.log(res);
		console.log(inputTitle);
		console.log(inputContent);
		window.location.href = `http://localhost:5173/article/${res.data.data}`;
	}

</script>



<div class="width-45 mr-auto ml-auto">
	<textarea class="textarea textarea-ghost text-4xl w-full" placeholder="제목을 입력해주세요." bind:value={inputTitle} />
	<div class="flex items-center space-x-2 mt-6" />
	<div class="divider divider-Neutral mb-1" />

	<div class="mt-8">
		<textarea class="textarea textarea-ghost mt-1 w-full h-screen" placeholder="내용을 입력해주세요." bind:value={inputContent} />
	</div>

	<div class="flex space-x-4 mb-6 container flex-wrap flex-row-reverse">

		<div class="flex-wrap">
		<button
			class="btn border-gray-600 btn-ghost"
			on:click={() => {
				postArticle();
				inputTitle = "";
				inputContent = "";
			}}>
			작성 완료
		</button>
		<button
			class="btn border-gray-600 btn-ghost "
			on:click={() => {
				inputTitle = "";
				inputContent = "";
			}}>
			취소
		</button>
	</div>
	</div>
</div>

