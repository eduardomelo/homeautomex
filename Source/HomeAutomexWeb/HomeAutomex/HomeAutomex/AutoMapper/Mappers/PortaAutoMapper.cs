using AutoMapper;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper.Mappers
{
    public class PortaAutoMapper : Profile
    {
        protected override void Configure()
        {
            Mapper.CreateMap<Porta, PortaModel>()
               .ForMember(e => e.ChaveModulo, opt => opt.MapFrom(e => e.Modulo.Chave))
               .ForMember(e => e.ChaveTipoPorta, opt => opt.MapFrom(e => e.Tipo.Chave));

            Mapper.CreateMap<PortaModel, Porta>();

            base.Configure();
        }
    }
}