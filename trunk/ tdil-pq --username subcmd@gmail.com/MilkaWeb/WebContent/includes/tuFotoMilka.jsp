<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MilkaPhotoUtils"%>
<%@page import="com.tdil.milka.model.valueobjects.MilkaPhotoValueObject"%>
<%@page import="java.util.List"%>
<% List<MilkaPhotoValueObject> milkaPhotos = MilkaPhotoUtils.getPhotos(); %>
<% List<MilkaPhotoValueObject> toDisplay = MilkaPhotoUtils.getDisplayPart(milkaPhotos); %>

<div id="fotos">
	<h2>TU FOTO MILKA</h2>
		<ul>
			<% 	int milkaPhotoIndex = 0;
				for (MilkaPhotoValueObject mfvo : toDisplay) { %>
					<% if (milkaPhotoIndex == 0) { %>
						<li class="f-grande"><a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" title="<%=mfvo.getImageTitle()%>" id="lk-<%=mfvo.getIdClickCounter()%>" rel="lightbox[pm]" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo"><img src="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" width="212" height="157" alt="foto1" /></a></li>
					<% } else { %>
						<li class="f-grande"><a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" title="<%=mfvo.getImageTitle()%>" id="lk-<%=mfvo.getIdClickCounter()%>" rel="lightbox[pm]" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo"><img src="./downloadThumb.st?id=<%=mfvo.getIdApprovedData()%>&width=66&height=66&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" alt="foto3" /></a></li>
					<% } %>
			<% 	milkaPhotoIndex = milkaPhotoIndex + 1;
				} %>
		</ul>
	</div>
	<!-- end fotos-->
</div>
<% List<MilkaPhotoValueObject> hiddenDisplay = MilkaPhotoUtils.getHiddenPart(milkaPhotos); %>
<div class="hide">
	<% for (MilkaPhotoValueObject mfvo : hiddenDisplay) { %>
		<a href="./download.st?id=<%=mfvo.getIdApprovedData()%>&type=PUBLIC&ext=<%=mfvo.getExtApprovedData()%>" id="lk-<%=mfvo.getIdClickCounter()%>" rel="lightbox[pm]" title="<%=mfvo.getImageTitle()%>" button="<%=mfvo.getIdClickCounter()%>-<%=MeltButton.meltButtonCount(mfvo.getIdClickCounter())%>" class="activo">a</a>
	<% } %>
</div>